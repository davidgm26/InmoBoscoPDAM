part of 'login_bloc_bloc.dart';

abstract class LoginBlocEvent extends Equatable {
  const LoginBlocEvent();

  @override
  List<Object> get props => [];
}

class LoginBlocDoLogin extends LoginBlocEvent {
  final String username;
  final String password;

  const LoginBlocDoLogin(this.username, this.password);
}
