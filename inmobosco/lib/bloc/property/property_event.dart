part of 'property_bloc.dart';

abstract class PropertyEvent extends Equatable {
  const PropertyEvent();

  @override
  List<Object> get props => [];
}

class PropertyFetch extends PropertyEvent{}

class PropertyRefresh extends PropertyEvent{}




