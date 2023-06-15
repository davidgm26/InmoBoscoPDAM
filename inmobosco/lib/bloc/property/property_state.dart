part of 'property_bloc.dart';

enum PropertyStatus { initial, success, failure}

class PropertyState extends Equatable {

  const PropertyState(

      {this.status = PropertyStatus.initial,
      this.properties = const <Property>[],
      this.hasReachedMax = false,
      this.page = 0
      });

  final PropertyStatus status;
  final List<Property> properties;
  final bool hasReachedMax;
  final int page;

  PropertyState copyWith(
      {PropertyStatus? status,
      List<Property>? properties,
      bool? hasReachedMax,
      int? page}) {
    return PropertyState(
        status: status ?? this.status,
        properties: properties ?? this.properties,
        hasReachedMax: hasReachedMax ?? this.hasReachedMax,
        page: page ?? this.page);
  }

  @override
  String toString() {
    return '''PropertyState { status: $status, hasReachedMax: $hasReachedMax, posts: ${properties.length} }''';
  }

  @override
  List<Object> get props => [status, properties, hasReachedMax, page];
}