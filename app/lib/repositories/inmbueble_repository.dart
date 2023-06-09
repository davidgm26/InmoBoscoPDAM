import 'dart:convert';

import 'package:injectable/injectable.dart';
import 'package:http/http.dart' as http;
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/inmuebles.dart';
import 'package:inmobosco/rest/rest.dart';

const _postLimit = 20;

String url_base = "http://localhost:8080";

@singleton
class InmuebleRepository {

  late RestAuthenticatedClient _client;

  InmuebleRepository() {
    _client = getIt<RestAuthenticatedClient>();
  }

  Future<InmuebleResponse> fetchInmuebles([int startIndex = 0]) async {

    String url = "/inmueble/?page=${startIndex}";  
  
    final response = await _client.get(url);
    
    final body = jsonDecode(response);

    return InmuebleResponse.fromJson(body);
   
  
    //throw Exception('error fetching posts');
  }



}