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
        child: Column(children: [
          const Text("Sunny", style: TextStyle(fontSize: 40)),
          const Text("-5C", style: TextStyle(fontSize: 40)),
          const Text("3 m/s", style: TextStyle(fontSize: 40)),
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
        ],)
      ),
    );
  }
}