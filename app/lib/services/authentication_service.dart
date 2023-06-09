import 'package:app/models/all_user_data.dart';
import 'package:app/models/login_request.dart';
import 'package:app/models/login_response.dart';
import 'package:app/repositories/authentication_repository.dart';
import 'package:app/repositories/user_repository.dart';
import 'package:app/services/local_storage_service.dart';
import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';

abstract class AuthenticationService {
  Future<UserDataResponse?> getCurrentUser();
  Future<LoginResponse> login(LoginRequest loginRequest);
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
    String token = _localStorageService.getFromDisk("user_token");
    if (token != null) {
      UserDataResponse response = await _userRepository.me();
      return response;
    }
    return null;
  }

  @override
  Future<LoginResponse> login(LoginRequest loginRequest) async {
    LoginResponse response =
        await _authenticationRepository.doLogin(loginRequest);
    _localStorageService.saveToDisk('token', response.token);
    return response;
  }

  @override
  Future<void> signOut() async {
    await _localStorageService.deleteFromDisk('token');
  }
}
