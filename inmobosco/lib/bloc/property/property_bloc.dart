import 'package:bloc/bloc.dart';
import 'package:bloc_concurrency/bloc_concurrency.dart';
import 'package:equatable/equatable.dart';
import 'package:inmobosco/models/property_response.dart';
import 'package:inmobosco/services/property_service.dart';
import 'package:stream_transform/stream_transform.dart';

part 'property_event.dart';
part 'property_state.dart';

const throttleDuration = Duration(milliseconds: 100);

EventTransformer<E> throttleDroppable<E>(Duration duration) {
  return (events, mapper) {
    return droppable<E>().call(events.throttle(duration), mapper);
  };
}

class PropertyBloc extends Bloc<PropertyEvent, PropertyState> {
  final PropertyService _propertyService;

  PropertyBloc(PropertyService propertyService)
      : assert(propertyService != null),
        _propertyService = propertyService,
        super(const PropertyState()) {
    on<PropertyFetch>(_onPropertyFetch);
    on<PropertyRefresh>(_onPropertyRefresh);
  }

  Future<void> _onPropertyFetch(
      PropertyFetch event, Emitter<PropertyState> emitter) async {
    if (state.hasReachedMax) return;
    try {
      if (state.status == PropertyStatus.initial) {
        final properties = await _propertyService.getAllProperties(state.page);
        return emitter(state.copyWith(
            status: PropertyStatus.success,
            properties: properties.content,
            hasReachedMax: properties.totalPages! - 1 <= state.page,
            page: state.page + 1));
      }
      final properties = await _propertyService.getAllProperties(state.page);
      emitter(state.copyWith(
          status: PropertyStatus.success,
          properties: List.of(state.properties)..addAll(properties.content!),
          hasReachedMax: properties.totalPages! - 1 <= state.page,
          page: state.page + 1));
    } catch (_) {
      emitter(state.copyWith(status: PropertyStatus.failure));
    }
  }

  Future<void> _onPropertyRefresh(
      PropertyRefresh event, Emitter<PropertyState> emitter) async {
    final properties = await _propertyService.getAllProperties(state.page);
    return emitter(state.copyWith(
        status: PropertyStatus.success,
        properties: List.of(state.properties)..addAll(properties.content!),
        hasReachedMax: properties.totalPages! - 1 <= state.page,
        page: state.page + 1));
  }
  
}
