import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco2/models/all_user_data.dart';
import 'package:inmobosco2/models/login_request.dart';
import 'package:inmobosco2/models/login_response.dart';
import 'package:inmobosco2/repository/auth-repository-impl.dart';
import 'package:inmobosco2/repository/user-repository-impl.dart';
import 'package:inmobosco2/services/localstorage_service.dart';

abstract class AuthenticationService {
  Future<UserDataResponse?> getCurrentUser();
  Future<LoginResponse> login(LoginRequest);
  Future<void> signOut();
}

@singleton
class JwtAuthenticationService extends AuthenticationService {
  late AuthRepositoryImpl _authenticationRepository;
  late LocalStorageService _localStorageService;
  late UserRepositoryImpl _userRepository;

  JwtAuthenticationService() {
    _authenticationRepository = GetIt.instance<AuthRepositoryImpl>();
    _userRepository = GetIt.instance<UserRepositoryImpl>();
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
  Future<LoginResponse> login(LoginRequest) async {
    LoginResponse response = await _authenticationRepository.doLogin(LoginRequest);
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
