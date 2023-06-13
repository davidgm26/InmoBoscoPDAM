import 'dart:convert';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco/models/all_user_data.dart';
import 'package:inmobosco/rest/rest.dart';
import 'package:inmobosco/services/services.dart';



@Order(-1)
@singleton
class UserRepository {

late RestAuthenticatedClient _client;
late LocalStorageService _localStorageService;

  UserRepository() {
    _client = GetIt.instance<RestAuthenticatedClient>();
    
    GetIt.I
        .getAsync<LocalStorageService>()
        .then((value) => _localStorageService = value);
  }

  Future<dynamic> me() async {
    String url = "/me";
    String? token = _localStorageService.getFromDisk("user_token");
    var jsonResponse = await _client.get(url,token!);
    return UserDataResponse.fromJson(jsonDecode(jsonResponse));

  }





}