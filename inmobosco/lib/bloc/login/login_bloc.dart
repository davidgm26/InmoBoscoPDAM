import 'package:bloc/bloc.dart';
import 'package:inmobosco/bloc/authentication/authentication_bloc.dart';
import 'package:inmobosco/bloc/authentication/authentication_event.dart';
import 'package:inmobosco/models/all_user_data.dart';
import 'package:inmobosco/rest/rest.dart';
import 'login_event.dart';
import 'login_state.dart';
import '../../exceptions/exceptions.dart';
import '../../services/services.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  final AuthenticationBloc _authenticationBloc;
  final AuthenticationService _authenticationService;

  LoginBloc(AuthenticationBloc authenticationBloc, AuthenticationService authenticationService)
      : assert(authenticationBloc != null),
        assert(authenticationService != null),
        _authenticationBloc = authenticationBloc,
        _authenticationService = authenticationService,
        super(LoginInitial()) {
          on<LoginInWithEmailButtonPressed>(__onLogingInWithEmailButtonPressed);
        }


  __onLogingInWithEmailButtonPressed(
    LoginInWithEmailButtonPressed event,
    Emitter<LoginState> emit,
  ) async {
    emit(LoginLoading());
    try {
      final user = await _authenticationService.login(event.email, event.password);
      User u = User(name:  user.firstname!, email: user.email!, accessToken: user.token!, roles:[user.rol!],refreshToken: user.refreshToken!);
      if (user != null) {
        _authenticationBloc.add(UserLoggedIn(user: u));
        emit(LoginSuccess());
        emit(LoginInitial());
      } else {
        emit(LoginFailure(error: 'Something very weird just happened'));
      }
    } on AuthenticationException catch (e) {
      emit(LoginFailure(error: e.message));
    } on CustomException catch (err) {
      emit(LoginFailure(error:'An unknown error occurred ${err.message}'));
    }
  }

  
}
