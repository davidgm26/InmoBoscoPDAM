import 'package:equatable/equatable.dart';
import 'package:inmobosco/models/property_response.dart';

enum PropertyStatus { initial, success, failure }

class PropertyState extends Equatable{
  const PropertyState({
    this.status = PropertyStatus.initial,
    this.propertyList = const <Property>[],
    this.hasReachedMax = false,
  });

  final PropertyStatus status;
  final List<Property> propertyList;
  final bool hasReachedMax;

  PropertyState copyWith({
    PropertyStatus? status,
    List<Property>? properties,
    bool? hasReachedMax,
  }) {
    return PropertyState(
      status: status ?? this.status,
      propertyList: properties ?? propertyList,
      hasReachedMax: hasReachedMax ?? this.hasReachedMax,
    );
  }

  @override
  String toString() {
    return '''PostFavouritesState { status: $status, hasReachedMax: $hasReachedMax, posts: ${propertyList.length} }''';
  }

  @override
  List<Object> get props => [status, propertyList, hasReachedMax];
}