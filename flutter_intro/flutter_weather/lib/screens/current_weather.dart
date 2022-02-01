import 'package:flutter/material.dart';
import 'package:flutter_weather/screens/forecast.dart';

class currentWeatherOnly extends StatelessWidget {
  const currentWeatherOnly({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Bangkok'),
      ),
      body: Center(
        child: ElevatedButton(
          child: const Text('Open forecast'),
          onPressed: () {
            // Navigate to second route when tapped.
            Navigator.push(
                context,
                // class we use to change to another page
                MaterialPageRoute(
                    builder: (context) => const WeatherForecastScreen()));
          },
        ),
      ),
    );
  }
}