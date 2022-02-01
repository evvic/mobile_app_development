import 'package:flutter/material.dart';
import 'package:flutter_weather/screens/forecast.dart';

class currentWeatherOnly extends StatefulWidget {
  const currentWeatherOnly({Key? key}) : super(key: key);

  @override
  State<currentWeatherOnly> createState() => _currentWeatherOnly();
}

class _currentWeatherOnly extends State<currentWeatherOnly> {
  String cityName = "Bangkok";
  String currentWeather = "Sunny";
  double temp = 0;
  double windSpeed = 0;

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
          Text("$temp C", style: TextStyle(fontSize: 40)),
          Text("$windSpeed m/s", style: TextStyle(fontSize: 40)),
          ElevatedButton(
            child: const Text('Get weather data'),
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
