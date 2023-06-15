import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/bloc/property/property_bloc.dart';
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/models.dart';
import 'package:inmobosco/services/property_service.dart';

class PropertyList extends StatelessWidget {
  const PropertyList({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context){
        final propertyService = getIt<PropertyService>();
        return PropertyBloc(propertyService)..add(PropertyFetch());
      },
      child: PropertyListBB()
    );

  }

}

class PropertyListBB extends StatefulWidget {
  const PropertyListBB({super.key});

  @override
  State<PropertyListBB> createState() => _PropertyListBBState();
}

class _PropertyListBBState extends State<PropertyListBB> {
  final _scrollController = ScrollController();
  
  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
  }
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<PropertyBloc,PropertyState>(
      builder: (context, state) {
        switch (state.status) {
          case PropertyStatus.failure:
          return Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Center(
                  child: Text('Failed to fetch properties'),
                ),
                ElevatedButton(
                  onPressed: () {
                    context.read<PropertyBloc>().add(PropertyRefresh());
                  },
                  style: const ButtonStyle(
                      backgroundColor:
                          MaterialStatePropertyAll(Colors.black87)),
                  child: const Text('Try again'),
                ),
              ],
            );        
          case PropertyStatus.success:
            if (state.properties.isEmpty) {
              return const Center(child: Text('no films'));
            }
            return ListView.builder(
              itemBuilder: (BuildContext context, int index) {
                return index >= state.properties.length
                    ? const CircularProgressIndicator()
                    : PropertyListItem(property: state.properties[index]);
              },
              itemCount: state.hasReachedMax
                  ? state.properties.length
                  : state.properties.length + 1,
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
    if (_isBottom) context.read<PropertyBloc>().add(PropertyFetch());
  }

  bool get _isBottom {
    if (!_scrollController.hasClients) return false;
    final maxScroll = _scrollController.position.maxScrollExtent;
    final currentScroll = _scrollController.offset;
    return currentScroll >= (maxScroll * 0.9);
  }
}

class PropertyListItem extends StatefulWidget {
  final Property property;
  const PropertyListItem({super.key,required this.property});

  @override
  State<PropertyListItem> createState() => PropertyListItemState();
}

class PropertyListItemState extends State<PropertyListItem> {

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    return Material(
      child: ListTile(
        leading: Text(widget.property.propertyType!, style: textTheme.bodySmall),
        title: Text(widget.property.name!),
        isThreeLine: true,
        subtitle: Text("${widget.property.price} €" ),
        dense: true,
      ),
    );
  }
}


  
