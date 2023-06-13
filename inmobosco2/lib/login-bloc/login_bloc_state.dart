part of 'login_bloc_bloc.dart';



abstract class LoginBlocState extends Equatable {
  const LoginBlocState();
  
  @override
  List<Object> get props => [];
}

class LoginBlocInitial extends LoginBlocState {}

class LoginBlocLoading extends LoginBlocState {}

class LoginBlocSuccess extends LoginBlocState {
  final LoginResponse loginResponse;

LoginBlocSuccess(this.loginResponse);
}

class LoginBlocError extends LoginBlocState {
  late final String message;
  
  LoginBlocError(this.message);
}


