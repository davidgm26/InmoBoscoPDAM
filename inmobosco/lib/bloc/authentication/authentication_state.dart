import 'package:equatable/equatable.dart';
import 'package:inmobosco/models/all_user_data.dart';


abstract class AuthenticationState extends Equatable {
  const AuthenticationState();

  @override
  List<Object> get props => [];

}

class AuthenticationInitial extends AuthenticationState {}

class AuthenticationLoading extends AuthenticationState {}

class AuthenticationNotAuthenticated extends AuthenticationState {}

class AuthenticationAuthenticated extends AuthenticationState {
  final User user;

  AuthenticationAuthenticated({required this.user});

  @override
  List<Object> get props => [user];
}

class AuthenticationFailure extends AuthenticationState {
  final String message;

  AuthenticationFailure({required this.message});

  @override
  List<Object> get props => [message];

}

class SessionExpiredState extends AuthenticationFailure {

  SessionExpiredState() : super(message: 'La sesión ha expirado, iniciela de nuevo');

  String get message => super.message;
  
@override
List<Object> get props => [message];

}