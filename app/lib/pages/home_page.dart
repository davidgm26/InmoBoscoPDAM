import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/Inmueble/inmueble.dart';
import 'package:inmobosco/repositories/inmbueble_repository.dart';
import 'package:inmobosco/blocs/authentication/authentication.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_bloc.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_event.dart';

class HomePage extends StatelessWidget {
  final User user;

  const HomePage({super.key, required this.user});

  @override
  Widget build(BuildContext context) {
    final authBloc = BlocProvider.of<AuthenticationBloc>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('InmoBosco'),
      ),
      body:
      BlocProvider(create: (_) => InmuebleBloc(InmuebleRepository())
      ..add(InmuebleFetched()),
      child: InmuebleList())
      
      /* SafeArea(
        minimum: const EdgeInsets.all(16),
        child: Center(
          child: Column(
            children: <Widget>[
              Text(
                'Welcome, ${user.nombre}',
                style: TextStyle(
                  fontSize: 24
                ),
              ),
              const SizedBox(
                height: 12,
              ),
              ElevatedButton(
                //textColor: Theme.of(context).primaryColor,
                /*style: TextButton.styleFrom(
                  primary: Theme.of(context).primaryColor,
                ),*/
                child: Text('Logout'),
                onPressed: (){
                  authBloc.add(UserLoggedOut());
                },
              ),
              ElevatedButton(onPressed: () async {
                print("Check");
                JwtAuthenticationService service = getIt<JwtAuthenticationService>();
                await service.getCurrentUser();
              }
              , child: Text('Check')
              )
            ],
          ),
        ),
      ),*/
    );
  }
}
