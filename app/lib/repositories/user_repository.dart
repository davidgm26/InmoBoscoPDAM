

import 'dart:convert';

import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/login.dart';
import 'package:inmobosco/models/user.dart';
import 'package:injectable/injectable.dart';

import 'package:inmobosco/rest/rest.dart';

@Order(-1)
@singleton
class UserRepository {

  late RestAuthenticatedClient _client;

  UserRepository() {
    _client = getIt<RestAuthenticatedClient>();
  }

  Future<dynamic> me() async {
    String url = "/me";

    var jsonResponse = await _client.get(url);
    return UserResponse.fromJson(jsonDecode(jsonResponse));

  }





}