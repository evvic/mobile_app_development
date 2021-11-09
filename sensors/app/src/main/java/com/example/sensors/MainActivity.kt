package com.example.sensors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var levelingStarted: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // find default accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        levelingStarted = true
    }

    fun calcLevel(view: android.view.View) {
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        Toast.makeText(this, "calc level", Toast.LENGTH_SHORT)

        for(sensor in sensorList) {
            Toast.makeText(this, sensor.name, Toast.LENGTH_SHORT)
        }

        if(accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }


    }


    override fun onSensorChanged(event: SensorEvent?) {
        val sensorXValue = event?.values?.get(0)
        val sensorYValue = event?.values?.get(1)
        val sensorZValue = event?.values?.get(2)

        val sensorTextView = findViewById<TextView>(R.id.sensorValues)
        sensorTextView.text = "X: " + sensorXValue + "\nY: " + sensorYValue + "\nZ: " + sensorZValue

        val bg = findViewById<ConstraintLayout>(R.id.backgroundView)

        //change bg color when x is almost 10 (horizontal)
        /* if (sensorXValue != null) {
            if(sensorXValue > 9.5) {
                //change background
                bg.setBackgroundColor(Color.YELLOW)
            }
            else {
                // change background to default
                bg.setBackgroundColor(Color.BLUE)
            }
        } */

        if(sensorXValue != null && sensorYValue != null) {
            if(sensorXValue > 9.5) {
                //change background
                bg.setBackgroundColor(Color.YELLOW)
            }
            else if(sensorXValue < 0.5 && sensorXValue > -0.5
                && sensorYValue < 0.5 && sensorYValue > -0.5) {
                bg.setBackgroundColor(Color.GREEN)
            }
            else {
                // change background to default
                bg.setBackgroundColor(Color.BLUE)
            }
        }

        //when all 3 axis are 0, change color
        //....
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        //start reading
        if(accelerometer != null && levelingStarted) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // release the sensor
        sensorManager.unregisterListener(this)
    }

}


