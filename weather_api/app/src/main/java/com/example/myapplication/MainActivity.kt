package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import layout.Item
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var lastKnownLocation: Location
    //listener vars
    private var latListener: Double = 0.0
    private var longListener: Double = 0.0
    // captured coordinates for API
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    // city name for API
    private var cityName: String = ""
    // volley
    private lateinit var requestQueue: RequestQueue

    // values to dispay
    private var temperature: Int = 0
    private var windSpeed: Double = 0.0
    private var description: String = ""
    // values TextViews
    private lateinit var tempView: TextView
    private lateinit var windView: TextView
    private lateinit var descView: TextView


    // btn for forecast view: initialize as disabled
    private lateinit var forecastBtn: Button
    private lateinit var citySearch: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        citySearch = findViewById(R.id.citySearch)
        tempView = findViewById(R.id.temperature)
        windView = findViewById(R.id.windspeed)
        descView = findViewById(R.id.description)

        requestQueue = Volley.newRequestQueue(this)

        initLocationUpdate() //get permission to use gps
        //make forecast btn disabled, need api call 1st to access
        forecastBtn = findViewById(R.id.forecastActivity)
        forecastBtn.isEnabled = false


        citySearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("text change", "before text change")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("text change", "on text change")
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("text change", "after text change")
            }

        })

    }

    fun forecastActivity(view: android.view.View) {
        // clicking this button should take the user to the lotto activity
        val intent = Intent(this, ForecastActivity::class.java)
        startActivity(intent)
    }

    fun captureGps(view: android.view.View) {
        // button to capture current coordiantes
        try {
            //Toast.makeText(this, "lat:$latListener", Toast.LENGTH_SHORT).show()
            latitude = latListener
            longitude = longListener


            parseJSON(city = false)
            // enable forecast btn
            forecastBtn.isEnabled = true
            
        } catch (e: Exception) {
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    0)
            }
            else {
                Toast.makeText(this, "error retrieving gps", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initLocationUpdate() {
        locationManager = getSystemService( Context.LOCATION_SERVICE ) as LocationManager

        //check if we have fine and course location permissions
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                0)
            //return
        }
        try {
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0f, this)
        } catch (e: Exception) {
            Toast.makeText(this, "caught error", Toast.LENGTH_SHORT).show()
            descView.text = e.message.toString()
        }
    }

    override fun onLocationChanged(location: Location) {
        // location info updated
        lastKnownLocation = location
        latListener = location.latitude
        longListener = location.longitude
    }

    // city boolean is a switch to determine if calling API with coordinates or city name
    private fun parseJSON(city: Boolean) {
        var url: String = ""

        // I should hide my API key but it's not a big deal...
        if(city == true) {
            // by city:
            url = "https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=6458e55e8621e80acd5796130cc32523"
        } else {
            // by coordinates:
            url = "https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=6458e55e8621e80acd5796130cc32523"
        }


        val request = JsonObjectRequest(
            Request.Method.GET, // method
            url, // url
            null, // json request
            { response -> // response listener

                try {
                    Toast.makeText(this, "description", Toast.LENGTH_SHORT).show()
                    val obj: JSONObject = response

                    //get coordinates and update lat & long
                    //in case city name API call was used
                    val coordObj = obj.getJSONObject("coord")
                    latitude = coordObj.getDouble("lat")
                    longitude = coordObj.getDouble("lon")

                    // get temperature
                    val tempObj = obj.getJSONObject("main")
                    temperature = (tempObj.getDouble("temp") - 273.15).toInt()


                    // get wind speed
                    val windObj = obj.getJSONObject("wind")
                    windSpeed = windObj.getDouble("speed")

                    // get description
                    val weatherObj: JSONObject = obj.getJSONArray("weather").get(0) as JSONObject
                    description = weatherObj.getString("description")

                    Toast.makeText(this, description, Toast.LENGTH_LONG).show()


                }catch (e: JSONException) {
                    Toast.makeText(this, "jsonexeption", Toast.LENGTH_LONG).show()
                }

            },
            { error -> // error listener
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                descView.text = error.message
            }
        )
        requestQueue.add(request)

        tempView.text = temperature.toString() + "°C"
        windView.text = windSpeed.toString() + "m/s"
        descView.text = description

    }





}