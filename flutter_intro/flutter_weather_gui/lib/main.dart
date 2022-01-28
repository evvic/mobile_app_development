import 'package:flutter/material.dart';

void main() {
  runApp(new MyApp());
}

class MyApp extends StatelessWidget {
  final ButtonStyle style =
    ElevatedButton.styleFrom(textStyle: const TextStyle(fontSize: 20));

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "edawg's Flutter Weather",
      theme: ThemeData(
        primarySwatch: Colors.blueGrey,
      ),
      home: Scaffold(
        backgroundColor: Colors.lightBlue,
        appBar: AppBar(
          title: const Text("edawg's Flutter Weather"),
        ),
        body: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Column(
                  children: <Widget>[
                    const Text('New York', style: TextStyle(color: Colors.white)),
                    Text('Sunny', style: new TextStyle(color: Colors.white, fontSize: 32.0)),
                    Text('15°C',  style: new TextStyle(color: Colors.white, fontSize: 16.0)),
                    Image.network('https://openweathermap.org/img/w/01d.png', scale: 0.6,),
                    Text('wind speed: 6 m/s', style: new TextStyle(color: Colors.white, fontSize: 16.0)),
                    Text('Jun 28, 2018', style: new TextStyle(color: Colors.white)),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: ElevatedButton(
                    style: style,
                    onPressed: () {},
                    child: const Text('Refresh'),

                  ),
              )
            ]
          )
        )
      ),
    );
  }
}