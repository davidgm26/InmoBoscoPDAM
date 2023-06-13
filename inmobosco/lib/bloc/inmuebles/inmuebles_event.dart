import 'package:equatable/equatable.dart';

abstract class InmuebleEvent extends Equatable {
  @override
  List<Object> get props => [];
}

class InmuebleFetched extends InmuebleEvent {}