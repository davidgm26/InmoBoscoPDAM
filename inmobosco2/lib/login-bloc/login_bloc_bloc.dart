import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:inmobosco2/models/login_request.dart';
import 'package:inmobosco2/models/login_response.dart';
import 'package:inmobosco2/repository/auth-repository.dart';

part 'login_bloc_event.dart';
part 'login_bloc_state.dart';

class LoginBlocBloc extends Bloc<LoginBlocEvent, LoginBlocState> {
  final AuthRepository _authRepository;

  LoginBlocBloc(this._authRepository) : super(LoginBlocInitial()) {
    on<LoginBlocDoLogin>(_doLogin);
  }

  Future<void> _doLogin(LoginBlocDoLogin event, Emitter<LoginBlocState> emit) async {
    try {
      final LoginRequest  loginRequest= LoginRequest (event.username,event.password);
      final LoginResponse response = await _authRepository.doLogin(loginRequest);
      emit(LoginBlocSuccess(response));

    } on Exception catch (e){
      emit(LoginBlocError(e.toString()));
    } 
  }
}
