import 'dart:convert';

import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco/models/login_response.dart';

import '../models/login_request.dart';
import '../rest/rest_client.dart';

@Order(-1)
@singleton  
class AuthenticationRepository {

  late RestClient _client;

  authenticationRepository(){
    _client = GetIt.I.get<RestClient>();
  }

  Future<dynamic> doLogin(String username , String password) async {
   String url = "/auth/login";
   var  jsonResponse = await _client.post(url, LoginRequest(username:username,password:password));
   return LoginResponse.fromJson(jsonDecode(jsonResponse));
  }


  
}