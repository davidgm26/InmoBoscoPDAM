import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:http/http.dart' as http;
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/models.dart';
import 'package:inmobosco/rest/rest.dart';

const _postLimit = 20;


@singleton
class PropertyRepository {

  late RestAuthenticatedClient _client;

  PropertyRepository() {
    _client = getIt<RestAuthenticatedClient>();
  }

  Future<PropertyResponse> fetchProperties(int index,String token) async {

    String url = "/property/?page=${index}";  
  
    final response = await _client.get(url,token);
    
    return PropertyResponse.fromJson(jsonDecode(response));
  
  }

}