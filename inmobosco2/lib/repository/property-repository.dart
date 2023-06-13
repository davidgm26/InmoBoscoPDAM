import 'package:inmobosco2/models/property_response.dart';

abstract class PropertyRepository {
  Future<PropertyResponse> getAllProperties(int numPage,String token);
  Future<PropertyResponse> getOneProperty(int id, String token);

}