import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/property_response.dart';
import 'package:inmobosco/rest/rest.dart';

const _postLimit = 20;

@singleton
class PropertyRepository {
  late RestAuthenticatedClient _client;

  PropertyRepository() {
    _client = getIt<RestAuthenticatedClient>();
  }

  Future<PropertyResponse> fetchProperties(int numPage, String token) async {
    String url = "/property/?page=${numPage}";

    final response = await _client.get(url, token);

    return PropertyResponse.fromJson(jsonDecode(response));
  }

  Future<Property> getOneProperty(int id, String token) async {
    String url = "/property/${id}";

    final resp = await _client.get(url, token);

    return Property.fromJson(jsonDecode(resp));
  }
}
