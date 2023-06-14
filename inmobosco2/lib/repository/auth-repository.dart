import 'package:inmobosco2/models/login_request.dart';
import 'package:inmobosco2/models/login_response.dart';

abstract class AuthRepository {
Future<LoginResponse> doLogin(LoginRequest loginRequest);
}