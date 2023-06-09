import 'dart:convert';

import 'package:app/models/login_request.dart';
import 'package:app/models/login_response.dart';
import 'package:app/rest/rest_client.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

@Order(-1)
@singleton  
class AuthenticationRepository {

  late RestClient _client;

  authenticationRepository(){
    _client = GetIt.I.get<RestClient>();
  }

  Future<dynamic> doLogin(LoginRequest loginRequest) async {
   String url = "/auth/login";
   var  jsonResponse = await _client.post(url, loginRequest);
   return LoginResponse.fromJson(jsonDecode(jsonResponse));
  }


  
}