import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/Inmueble/widgets/Inmueble_list_item.dart';
import 'package:inmobosco/Inmueble/widgets/bottom_loader.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_bloc.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_event.dart';
import 'package:inmobosco/bloc/inmuebles/inmuebles_state.dart';
class PropertyList extends StatefulWidget {
  const PropertyList ({super.key});

  @override
  State<PropertyList> createState() => _PropertyListState();
}

class _PropertyListState extends State<PropertyList> {
  final _scrollController = ScrollController();

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<InmuebleBloc, PropertyState>(
      builder: (context, state) {
        switch (state.status) {
          case PropertyStatus.failure:
            return Column(
                            mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Center(
                  child: Text('Failed to fetch items'),
                ),
                ElevatedButton(
                  onPressed: () {
                    context.read<InmuebleBloc>().add((InmuebleFetched()));
                  },
                  style: const ButtonStyle(
                      backgroundColor:
                          MaterialStatePropertyAll(Colors.black87)),
                  child: const Text('Actualizar'),
                ),
              ],
            );
          case PropertyStatus.success:
            if (state.propertyList.isEmpty) {
              return const Center(child: Text('no items'));
            }
            return ListView.builder(
              itemBuilder: (BuildContext context, int index) {
                return index >= state.propertyList.length
                    ? const BottomLoader()
                    : PropertyListItem(property: state.propertyList[index]);
              },
              itemCount: state.hasReachedMax
                  ? state.propertyList.length
                  : state.propertyList.length + 1,
              controller: _scrollController,
            );
          case PropertyStatus.initial:
            return const Center(child: CircularProgressIndicator());
        }
      },
    );
  }

  @override
  void dispose() {
    _scrollController
      ..removeListener(_onScroll)
      ..dispose();
    super.dispose();
  }

  void _onScroll() {
    if (_isBottom) context.read<InmuebleBloc>().add(InmuebleFetched());
  }

  bool get _isBottom {
    if (!_scrollController.hasClients) return false;
    final maxScroll = _scrollController.position.maxScrollExtent;
    final currentScroll = _scrollController.offset;
    return currentScroll >= (maxScroll * 0.9);
  }
}