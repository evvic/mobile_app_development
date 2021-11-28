package com.example.devicemanagers

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import java.util.*

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var lastKnownLocation: Location
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initLocationUpdate()
    }

    fun startGps(view: android.view.View) {
        //start GPS here
        initLocationUpdate()
    }

    fun initLocationUpdate() {
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
        lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
    }

    override fun onLocationChanged(location: Location) {
        // location info updated
        lastKnownLocation = location
        latitude = location.latitude
        longitude = location.longitude

        var latTextView: TextView = findViewById(R.id.xCoordinate)
        var longTextView: TextView = findViewById(R.id.yCoordinate)

        latTextView.setText("Lat: " + latitude)
        longTextView.setText("Long: " + longitude)

    }

    fun showMap(view: android.view.View){
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                0)
        }
        val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }
}