import 'package:equatable/equatable.dart';
import 'package:inmobosco/models/inmuebles.dart';

enum InmuebleStatus { initial, success, failure }

class InmuebleState extends Equatable{
  const InmuebleState({
    this.status = InmuebleStatus.initial,
    this.inmubleList = const <Inmueble>[],
    this.hasReachedMax = false,
  });

  final InmuebleStatus status;
  final List<Inmueble> inmubleList;
  final bool hasReachedMax;

  InmuebleState copyWith({
    InmuebleStatus? status,
    List<Inmueble>? inmuebles,
    bool? hasReachedMax,
  }) {
    return InmuebleState(
      status: status ?? this.status,
      inmubleList: inmuebles ?? inmubleList,
      hasReachedMax: hasReachedMax ?? this.hasReachedMax,
    );
  }

  @override
  String toString() {
    return '''PostFavouritesState { status: $status, hasReachedMax: $hasReachedMax, posts: ${inmubleList.length} }''';
  }

  @override
  List<Object> get props => [status, inmubleList, hasReachedMax];
}