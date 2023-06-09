part of 'login_bloc_bloc.dart';

@immutable
abstract class LoginBlocState {}

class InitialLogin extends LoginBlocState {}

class DoingLogin extends LoginBlocState {}

class LoginSuccess extends LoginBlocState {}

class LoginError extends LoginBlocState {}
