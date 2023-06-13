import 'package:bloc/bloc.dart';
import 'package:inmobosco/models/all_user_data.dart';
import 'package:inmobosco/rest/rest.dart';
import 'authentication_event.dart';
import 'authentication_state.dart';
import '../../services/services.dart';

class AuthenticationBloc extends Bloc<AuthenticationEvent, AuthenticationState> {
  final AuthenticationService _authenticationService;
  final LocalStorageService _localStorageService;

  AuthenticationBloc(AuthenticationService authenticationService, LocalStorageService localStorageService)
      : assert(authenticationService != null),
        _authenticationService = authenticationService,
        _localStorageService = localStorageService,
        super(AuthenticationInitial()) {
          on<AppLoaded>(_onAppLoaded);
          on<UserLoggedIn>(_onUserLoggedIn);
          on<UserLoggedOut>(_onUserLoggedOut);
          on<SessionExpiredEvent>(_onSessionExpired);
        }


  _onAppLoaded(
    AppLoaded event,
    Emitter<AuthenticationState> emit,
  ) async {
      emit(AuthenticationLoading());
      try {
        await Future.delayed(Duration(milliseconds: 500)); // a simulated delay
        final currentUser = await _authenticationService.getCurrentUser();
        User u = User(name:  currentUser!.firstname, email: currentUser.email!, accessToken: _localStorageService.getFromDisk('token'), roles:[currentUser.rol!],refreshToken: _localStorageService.getFromDisk('refresh_token'));

        if (currentUser != null) {
          emit(AuthenticationAuthenticated(user: u));
        } else {
          emit(AuthenticationNotAuthenticated());
        }
      } on UnauthorizedException catch (e) {
        print(e);
        emit(AuthenticationNotAuthenticated());  
      } on Exception catch (e) {
        emit(AuthenticationFailure(message: 'An unknown error occurred: ${e.toString()}'));
      }
  }

  _onUserLoggedIn(
    UserLoggedIn event,
    Emitter<AuthenticationState> emit,
   ) async {
    emit(AuthenticationAuthenticated(user: event.user));
  }

  _onUserLoggedOut(
    UserLoggedOut event,
    Emitter<AuthenticationState> emit,
  ) async {
    await _authenticationService.signOut();
    emit(AuthenticationNotAuthenticated());
  }

  _onSessionExpired(
    SessionExpiredEvent event,
    Emitter<AuthenticationState> emit,
  ) async {
    //emit(AuthenticationFailure(message: 'An unknown error occurred: ${e.toString()}'));
    print("sesión expirada");
    await _authenticationService.signOut();
    emit(SessionExpiredState());
  }

}