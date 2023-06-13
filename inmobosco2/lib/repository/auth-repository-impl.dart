import 'dart:convert';
import 'dart:io';

import 'package:get_it/get_it.dart';
import 'package:http/http.dart';
import 'package:inmobosco2/models/login_request.dart';
import 'package:inmobosco2/models/login_response.dart';
import 'package:inmobosco2/repository/auth-repository.dart';
import 'package:inmobosco2/rest_client.dart';

class AuthRepositoryImpl extends AuthRepository {
  late RestClient _client;

    authenticationRepository(){
    _client = GetIt.I.get<RestClient>();
  }

  @override
  Future<LoginResponse> doLogin(LoginRequest loginRequest) async {
    String url= "auth/login";
    final response = await _client.post(url,loginRequest);
    if (response.statusCode == 200) {
      return LoginResponse.fromJson(json.decode(response.body));
    } else {
      throw Exception('Fail to do login');
    }
  }
}
