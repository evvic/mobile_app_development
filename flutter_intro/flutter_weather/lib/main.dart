import 'package:flutter/material.dart';

void main() {
  runApp();
}

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
          child: const Text('Open route'),
          onPressed: () {
            // Navigate to second route when tapped.
            Navigator.push(
                context,
                MaterialPageRoute(
                    builder: (context) => const WeatherForecastScreen()));
          },
        ),
      ),
    );
  }
}

class WeatherForecastScreen extends StatelessWidget {
  const WeatherForecastScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Second Route'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            // Navigate back to first route when tapped.
          },
          child: const Text('Go back!'),
        ),
      ),
    );
  }
}
