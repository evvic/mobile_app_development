import 'package:bmi_app/components/person.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class BmiGui extends StatelessWidget {
  Person edawg = Person("Eric", 23);

  final ButtonStyle style =
    ElevatedButton.styleFrom(textStyle: const TextStyle(fontSize: 20));

  @override
  Widget build(BuildContext context) {
    return (
      Center(
        child: Container(
          width: 320,
          child: Card(
            color: Colors.white,
            elevation: 10,
            child: Padding (
              padding: const EdgeInsets.all(20),
              child: Column (
                mainAxisSize: MainAxisSize.min,
                children: [
                  const Text("Weight (kg)"),
                  const TextField(
                    decoration: InputDecoration(
                      border: InputBorder.none,
                      hintText: "Enter weight here",
                    ),
                  ),
                  const Text("Height (cm)"),
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
                  const SizedBox(
                    height: 20,
                  ),
                  Container(
                    child: Text(
                      edawg.toString(),
                      textAlign: TextAlign.center,
                    ),
                  )



        ]
        )
        )
        )
        )
      ,
    ));
  }
}
