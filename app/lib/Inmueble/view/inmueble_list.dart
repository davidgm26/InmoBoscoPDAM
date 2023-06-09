import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:inmobosco/Inmueble/inmueble.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_bloc.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_event.dart';
import 'package:inmobosco/blocs/inmuebles/inmuebles_state.dart';

class InmuebleList extends StatefulWidget {
  const InmuebleList({super.key});

  @override
  State<InmuebleList> createState() => _InmuebleListState();
}

class _InmuebleListState extends State<InmuebleList> {
  final _scrollController = ScrollController();

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<InmuebleBloc, InmuebleState>(
      builder: (context, state) {
        switch (state.status) {
          case InmuebleStatus.failure:
            return const Center(child: Text('failed to fetch posts'));
          case InmuebleStatus.success:
            if (state.inmubleList.isEmpty) {
              return const Center(child: Text('no posts'));
            }
            return ListView.builder(
              itemBuilder: (BuildContext context, int index) {
                return index >= state.inmubleList.length
                    ? const BottomLoader()
                    : InmuebleListItem(inmueble: state.inmubleList[index]);
              },
              itemCount: state.hasReachedMax
                  ? state.inmubleList.length
                  : state.inmubleList.length + 1,
              controller: _scrollController,
            );
          case InmuebleStatus.initial:
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