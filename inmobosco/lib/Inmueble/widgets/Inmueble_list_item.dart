import 'package:flutter/material.dart';
import 'package:inmobosco/models/inmuebles.dart';
import 'package:inmobosco/models/property_response.dart';

class PropertyListItem extends StatelessWidget {

  const PropertyListItem({super.key, required this.property});

  final Property property;

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    return Card(
        clipBehavior: Clip.antiAlias,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(26)),
        child: Column(
          children: [
            Stack(
              alignment: Alignment.center,
              children: [
                Ink.image(
                  image: NetworkImage(
                       "https://areajugones.sport.es/wp-content/uploads/2020/12/zoneri-021-headquarters-garrison.jpg"
                  ),
                  height: 240,
                  fit: BoxFit.cover,
                )
              ],
            ),
            const SizedBox(
              height: 8,
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(15, 0, 15, 15),
              child: Container(
                  color: Colors.white,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisSize: MainAxisSize.min,
                    children: <Widget>[
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            property.name!,
                            style: const TextStyle(
                                color: Colors.black,
                                fontWeight: FontWeight.bold,
                                fontSize: 14),
                          ),
                          Row(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              const Icon(
                                Icons.star,
                                color: Colors.black,
                                size: 16,
                              )
                            ],
                          ),
                        ],
                      ),
                      const SizedBox(height: 5),
                      Text(
                        "${property.price} €",
                        style: const TextStyle(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: 14,
                        ),
                      ),
                      const SizedBox(height: 5),
                      Text(
                        property.city!,
                        style: const TextStyle(
                          color: Colors.black,
                          fontSize: 12,
                        ),
                      ),
                    ],
                  )),
            ),
          ],
        ));
  }
}

