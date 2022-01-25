import 'package:flutter/cupertino.dart';

import 'package:flutter/material.dart';

class BmiGui extends StatelessWidget {
  final ButtonStyle style =
    ElevatedButton.styleFrom(textStyle: const TextStyle(fontSize: 20));

  @override
  Widget build(BuildContext context) {
    return (Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisSize: MainAxisSize.min,
        children: <Widget> [
          const Text("Weight"),
          const TextField(
            decoration: InputDecoration(
              border: InputBorder.none,
              hintText: "Enter weight here",
            ),
          ),
          const Text("Height"),
          const TextField(
            decoration: InputDecoration(
              border: InputBorder.none,
              hintText: "Enter height here",
            ),
          ),
          ElevatedButton(
            style: style,
            onPressed: () {},
            child: const Text('enabled'),

          ),
        ]
      ,
    ));
  }
}
