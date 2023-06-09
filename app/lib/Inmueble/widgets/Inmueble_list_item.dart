import 'package:flutter/material.dart';
import 'package:inmobosco/models/inmuebles.dart';

class InmuebleListItem extends StatelessWidget {
  const InmuebleListItem({super.key, required this.inmueble});

  final Inmueble inmueble;

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    return Material(
      child: ListTile(
        leading: Text('${inmueble.tipo}', style: textTheme.bodySmall),
        title: Text('${inmueble.ubicacion}'),
        isThreeLine: true,
        subtitle: Text('${inmueble.descripcion}'),
        dense: true,
      ),
    );
  }
}

