import 'dart:convert';

import 'package:inmobosco2/models/all_user_data.dart';
import 'package:inmobosco2/repository/user-repository.dart';
import 'package:inmobosco2/rest_client.dart';
import 'package:inmobosco2/services/localstorage_service.dart';

class UserRepositoryImpl extends UserRepository {

late RestAuthenticatedClient _client;
late LocalStorageService _localStorageService;
  @override
  Future me() async {
    String url = "/me";
    String? token = _localStorageService.getFromDisk("user_token");
    var jsonResponse = await _client.get(url,token!);
    return UserDataResponse.fromJson(jsonDecode(jsonResponse));
  }
  
}