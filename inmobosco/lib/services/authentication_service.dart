
import 'dart:convert';
//import 'dart:developer';

import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/services/localstorage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

//import '../exceptions/exceptions.dart';
import 'package:inmobosco/models/models.dart';
import 'package:inmobosco/repositories/repositories.dart';

abstract class AuthenticationService {
  Future<User?> getCurrentUser();
  Future<User> signInWithEmailAndPassword(String email, String password);
  Future<void> signOut();
}
@Order(2)
@singleton
class JwtAuthenticationService extends AuthenticationService {

  late AuthenticationRepository _authenticationRepository;
  late LocalStorageService _localStorageService;
  late UserRepository _userRepository;

  JwtAuthenticationService() {
    _authenticationRepository = getIt<AuthenticationRepository>();
    _userRepository = getIt<UserRepository>();
    GetIt.I.getAsync<LocalStorageService>().then((value) => _localStorageService = value);
  }


  @override
  Future<User?> getCurrentUser() async {
    print("get current user");
    String? token = _localStorageService.getFromDisk("user_token");
    if (token != null) {
      UserResponse response = await _userRepository.me();
      return response;
    }
    return null;
  }

  @override
  Future<User> signInWithEmailAndPassword(String email, String password) async {
    LoginResponse response = await _authenticationRepository.doLogin(email, password);
    await _localStorageService.saveToDisk('user_token', response.token);
    return User.fromLoginResponse(response);
  }

  @override
  Future<void> signOut() async {
    print("borrando token");
    await _localStorageService.deleteFromDisk("user_token");
  }

}