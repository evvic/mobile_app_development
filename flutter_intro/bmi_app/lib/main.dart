import 'package:flutter/material.dart';

void main() {
  runApp(MyBmiApp());
}

//everything is a widget: either stateless or statefull

class MyBmiApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Bmi App",
      theme: ThemeData(primarySwatch: Colors.red),
      home: MyBmiPage(),
    );
  }
}

class MyBmiPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("BMI App"),
      ),
      body: const Center(
        child: Text("Hello World"),
        )
    );
  }

}