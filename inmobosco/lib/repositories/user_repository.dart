

import 'dart:convert';

import 'package:get_it/get_it.dart';
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/login.dart';
import 'package:inmobosco/models/user.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco/services/localstorage_service.dart';
import 'package:inmobosco/rest/rest.dart';

@Order(-1)
@singleton
class UserRepository {

  late RestAuthenticatedClient _client;
late LocalStorageService _localStorageService;
  UserRepository() {
    _client = getIt<RestAuthenticatedClient>();
    
    GetIt.I
        .getAsync<LocalStorageService>()
        .then((value) => _localStorageService = value);
  }

  Future<dynamic> me() async {
    String url = "/me";
    String? token = _localStorageService.getFromDisk("user_token");
    var jsonResponse = await _client.get(url,token!);
    return UserResponse.fromJson(jsonDecode(jsonResponse));

  }





}