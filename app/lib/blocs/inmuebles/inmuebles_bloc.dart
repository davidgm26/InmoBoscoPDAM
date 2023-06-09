import 'package:bloc_concurrency/bloc_concurrency.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_event.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_state.dart';
import 'package:inmobosco/repositories/inmbueble_repository.dart';
import 'package:stream_transform/stream_transform.dart';

const throttleDuration = Duration(milliseconds: 100);
int page = -1;

EventTransformer<E> throttleDroppable<E>(Duration duration) {
  return (events, mapper) {
    return droppable<E>().call(events.throttle(duration), mapper);
  };
}

class InmuebleBloc extends Bloc<InmuebleEvent, InmuebleState> {
  InmuebleBloc  (this.inmuebleRepository) : super(const InmuebleState()) {
    on<InmuebleFetched>(
      _onInmuebleFetched,
      transformer: throttleDroppable(throttleDuration),
    );
  }

  final InmuebleRepository inmuebleRepository;

  Future<void> _onInmuebleFetched(
      InmuebleFetched event, Emitter<InmuebleState> emitter) async {
    if (state.hasReachedMax) return;
    try {
      if (state.status == InmuebleStatus.initial) {
        page = 0;
        final response = await inmuebleRepository.fetchInmuebles(page);
        return emitter(state.copyWith(
          status: InmuebleStatus.success,
          inmuebles: response.inmueble,
          hasReachedMax: response.totalPages - 1 <= page,
        ));
      }
      page += 1;
      final response = await inmuebleRepository.fetchInmuebles(page);

      emitter(state.copyWith(
          status: InmuebleStatus.success,
          inmuebles: List.of(state.inmubleList)..addAll(response.inmueble),
          hasReachedMax: response.totalPages - 1 <= page));
    } catch (_) {
      emitter(state.copyWith(status: InmuebleStatus.failure));
    }
  }
}