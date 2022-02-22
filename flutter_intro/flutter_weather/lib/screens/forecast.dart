import 'dart:convert'; //json
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


class WeatherForecastScreen extends StatefulWidget {
  const WeatherForecastScreen({Key? key}) : super(key: key);

  @override
  State<WeatherForecastScreen> createState() => _WeatherForecastScreen();
}

class _WeatherForecastScreen extends State<WeatherForecastScreen> {
  final List<String> entries = <String>['A', 'B', 'C'];

  void fetchWeather(String city) async {
    city = "Sacramento";

    Uri url = Uri.parse(
        "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=6c433438776b5be4ac86001dc88de74d");
    final response = await http.get(url);

    if (response.statusCode == 200) {
      // good response

      var weatherData = json.decode(response.body);

      setState(() {
        cityName = city;
        temp = weatherData['main']['temp'] - 273.15;
        currentWeather = weatherData['weather'][0]['description'];
        windSpeed = weatherData['wind']['speed'];
        icon = weatherData['weather'][0]['icon'];
      });

      updateGUI(); // set state
    }
  }

  @override
  // ignore: dead_code, dead_code
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Forecast'),
      ),
      body: ListView.separated(
        padding: const EdgeInsets.all(8),
        itemCount: entries.length,
        itemBuilder: (BuildContext context, int index) {
          return Container(
            height: 50,
            color: Colors.amber[400],
            child: Center(child: Text('Entry ${entries[index]}')),
          );
        },
        separatorBuilder: (BuildContext context, int index) => const Divider(),
      )
    );
  }
}