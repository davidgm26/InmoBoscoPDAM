import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/Inmueble/view/inmueble_list.dart';
import 'package:inmobosco/Inmueble/view/views.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_bloc.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_event.dart';
import 'package:inmobosco/services/property_service.dart';

class PostsPage extends StatelessWidget {
  const PostsPage({super.key, required this.propertyService});
  final PropertyService propertyService;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Posts')),
      body: BlocProvider(
        create: (_) => InmuebleBloc(propertyService)..add(InmuebleFetched()),
        child: const PropertyList(),
      ),
    );
  }
}