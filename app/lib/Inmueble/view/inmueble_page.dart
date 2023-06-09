import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/repositories/inmbueble_repository.dart';
import 'package:inmobosco/Inmueble/view/inmueble_list.dart';
import 'package:inmobosco/Inmueble/view/views.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_bloc.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_event.dart';

class PostsPage extends StatelessWidget {
  const PostsPage({super.key, required this.inmuebleRepository});
  final InmuebleRepository inmuebleRepository;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Posts')),
      body: BlocProvider(
        create: (_) => InmuebleBloc(inmuebleRepository)..add(InmuebleFetched()),
        child: const InmuebleList(),
      ),
    );
  }
}