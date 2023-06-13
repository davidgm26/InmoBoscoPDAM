import 'dart:convert';

import 'package:inmobosco2/models/property_response.dart';
import 'package:inmobosco2/repository/property-repository.dart';
import 'package:inmobosco2/rest_client.dart';

class PropertyRepositoryImpl extends PropertyRepository{
  late RestClient _client;

  @override
  Future<PropertyResponse> getAllProperties(int numPage,String token) async {
    String url = "/property/?page=${numPage}";
    final response = await _client.get(url,token);
    if(response.statuCode == 200){
    return PropertyResponse.fromJson(jsonDecode(response));
    }else{
      throw Exception('Fail to load properties');
    }
  }

  @override
  Future<PropertyResponse> getOneProperty(int id, String token) {
    String url = "/property/${id}";
    
    throw UnimplementedError();
  }
  
}