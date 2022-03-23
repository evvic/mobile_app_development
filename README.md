# mobile_app_development
Mobile App Development 2 course from TAMK (fall 2021)

> :warning: **Exercises 8-10 are in the same project**: only diffference is the commit that completed each exercise.

## Week 8
#### 8.1 / 7.3 Fetch weather data
https://github.com/evvic/mobile_app_development/tree/main/flutter_intro/flutter_weather
###### [Commit link](https://github.com/evvic/mobile_app_development/commit/dfdb866d2f6762212590200464211e2e303d20d2)


I followed the lesson in class today and replicated what was shown. Basically I created two screens that can be navigated between, but most importantly, I created the API call using http to get the JSON weather data of the given city.

## Week 9
#### Flutter Device APIs – part II
###### [Commit link](https://github.com/evvic/mobile_app_development/commit/50a4c56500b102334b4780bb6f781c427393b9de)

I continued off the weather app and used the users location as a method for the api call. I simply followed the Flutter docuentation to implement enabling service and asking for the users permission. The getLocation function is called when the user presses on the ”Get location” button, which then also triggers the api call.

The UI shows the coordinates but also shows all the relevant weather data for the user. All this is done by parsing the JSON response then updating the state.

## Week 10
#### weather api
https://github.com/evvic/mobile_app_development/tree/main/flutter_intro/flutter_weather
###### [Commit link](https://github.com/evvic/mobile_app_development/commit/dfdb866d2f6762212590200464211e2e303d20d2)

With following class, I created a ListView that shows the 10 day forecast for the given location through an api call using coordiantes. Each item in the list shows the day, temperature, and description of the weather.

I used a similar method to when I created the forecast on my Kotlin assignment, using a switch statement for the days (I won’t show it because the code is the same and very long), then parsing the temperature and description from the JSON object saved in state.
