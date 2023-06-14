import 'package:bloc_concurrency/bloc_concurrency.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_event.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_state.dart';
import 'package:inmobosco/services/property_service.dart';
import 'package:stream_transform/stream_transform.dart';

const throttleDuration = Duration(milliseconds: 100);
int page = -1;

EventTransformer<E> throttleDroppable<E>(Duration duration) {
  return (events, mapper) {
    return droppable<E>().call(events.throttle(duration), mapper);
  };
}

class InmuebleBloc extends Bloc<InmuebleEvent, PropertyState> {
  InmuebleBloc  (this.propertyService) : super(const PropertyState()) {
    on<InmuebleFetched>(
      _onInmuebleFetched,
      transformer: throttleDroppable(throttleDuration),
    );
  }

  final PropertyService propertyService;

  Future<void> _onInmuebleFetched(
      InmuebleFetched event, Emitter<PropertyState> emitter) async {
    if (state.hasReachedMax) return;
    try {
      if (state.status == PropertyStatus.initial) {
        page = 0;
        final response = await propertyService.getAllProperties(page);
        return emitter(state.copyWith(
          status: PropertyStatus.success,
          properties: response.content,
          hasReachedMax: response.totalPages! - 1 <= page,
        ));
      }
      page += 1;
      final response = await propertyService.getAllProperties(page);

      emitter(state.copyWith(
          status: PropertyStatus.success,
          properties: List.of(state.propertyList)..addAll(response.content!),
          hasReachedMax: response.totalPages! - 1 <= page));
    } catch (_) {
      emitter(state.copyWith(status: PropertyStatus.failure));
    }
  }
}