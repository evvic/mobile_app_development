import 'dart:convert'; // JSON converters

import 'package:flutter/material.dart';
import 'package:flutter_weather/screens/forecast.dart';
import 'package:http/http.dart' as http;
import 'package:sensors_plus/sensors_plus.dart';

class CurrentWeatherOnly extends StatefulWidget {
  const CurrentWeatherOnly({Key? key}) : super(key: key);

  @override
  State<CurrentWeatherOnly> createState() => _CurrentWeatherOnly();
}

class _CurrentWeatherOnly extends State<CurrentWeatherOnly> {
  String cityName = "Bangkok";
  String currentWeather = "Sunny";
  double temp = 0;
  double windSpeed = 0;
  String icon = "01n";

  void updateGUI() {}

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

  void openWeatherForecast() {
    
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(cityName),
      ),
      body: Center(
          child: Column(
        // evenly space all children vertically on the one screen
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          Text(currentWeather, style: TextStyle(fontSize: 40)),
          Image.network(
            'https://openweathermap.org/img/wn/$icon.png',
            scale: 0.8,
          ),
          Text(temp.toStringAsFixed(2) + " C", style: TextStyle(fontSize: 40)),
          Text("$windSpeed m/s", style: TextStyle(fontSize: 40)),
          ElevatedButton(
            child: const Text('Get weather data'),
            onPressed: () {
              // fetch data from internet
              fetchWeather('Sactown');
            },
          ),
          ElevatedButton(
            child: const Text('Forecast'),
            onPressed: () {
              // Navigate to second route when tapped.
              Navigator.push(
                  context,
                  // class we use to change to another page
                  MaterialPageRoute(
                      builder: (context) => const WeatherForecastScreen()));
            },
          ),
        ],
      )),
    );
  }

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    throw UnimplementedError();
  }
}
