
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco/models/all_user_data.dart';
import 'package:inmobosco/models/login_response.dart';
import 'package:inmobosco/repositories/authentication_repository.dart';
import 'package:inmobosco/repositories/user_repository.dart';
import 'package:inmobosco/services/services.dart';

abstract class AuthenticationService {
  Future<UserDataResponse?> getCurrentUser();
  Future<LoginResponse> login(String username, String password);
  Future<void> signOut();
}

@singleton
class JwtAuthenticationService extends AuthenticationService {
  late AuthenticationRepository _authenticationRepository;
  late LocalStorageService _localStorageService;
  late UserRepository _userRepository;

  JwtAuthenticationService() {
    _authenticationRepository = GetIt.instance<AuthenticationRepository>();
    _userRepository = GetIt.instance<UserRepository>();
    GetIt.I
        .getAsync<LocalStorageService>()
        .then((value) => _localStorageService = value);
  }

  @override
  Future<UserDataResponse?> getCurrentUser() async {
    String token = _localStorageService.getFromDisk("token");
    if (token != null) {
      UserDataResponse response = await _userRepository.me();
      return response;
    }
    return null;
  }

  @override
  Future<LoginResponse> login(String username , String password) async {
    LoginResponse response =
        await _authenticationRepository.doLogin(username,password);
    _localStorageService.saveToDisk('token', response.token);
        _localStorageService.saveToDisk('refresh_token', response.refreshToken);

    return response;
  }

  @override
  Future<void> signOut() async {
    await _localStorageService.deleteFromDisk('token');
    await _localStorageService.deleteFromDisk('refresh_token');
  }
}
