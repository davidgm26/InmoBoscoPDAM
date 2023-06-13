import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'fetch_property_list_event.dart';
part 'fetch_property_list_state.dart';

class FetchPropertyListBloc extends Bloc<FetchPropertyListEvent, FetchPropertyListState> {
  FetchPropertyListBloc() : super(FetchPropertyListInitial()) {
    on<FetchPropertyListEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
