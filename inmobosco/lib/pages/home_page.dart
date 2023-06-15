import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/Inmueble/view/property_list.dart';
import 'package:inmobosco/bloc/property/property_bloc.dart';
import 'package:inmobosco/models/user.dart';
import 'package:inmobosco/repositories/property_repository.dart';
import 'package:inmobosco/bloc/authentication/authentication.dart';
import 'package:inmobosco/services/property_service.dart';

class HomePage extends StatelessWidget {
  final User user;

  const HomePage({super.key, required this.user});

  @override
  Widget build(BuildContext context) {
    final authBloc = BlocProvider.of<AuthenticationBloc>(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('InmoBosco'),
        leading:IconButton(icon: Icon(Icons.logout),
        onPressed: () {
          BlocProvider.of<AuthenticationBloc>(context).add(UserLoggedOut());
        }),
        backgroundColor: Colors.red,
      ),
      body:
      PropertyList(),
      bottomNavigationBar: BottomNavigationBar(
        backgroundColor:Colors.red,
        unselectedItemColor: Colors.white,
        selectedItemColor: Colors.blueAccent, 
        items: const <BottomNavigationBarItem>[
        
          BottomNavigationBarItem(icon: Icon(Icons.home_outlined), label: 'Lista',backgroundColor: Colors.red),
          BottomNavigationBarItem(icon: Icon(Icons.face), label: 'Perfil'),
          BottomNavigationBarItem(icon: Icon(Icons.settings), label: 'Configuracion'),
          BottomNavigationBarItem(icon: Icon(Icons.add), label: 'Crear'), // new button
        ]
    ));
  }
}
