package com.example.mytorch

import android.graphics.Color
import android.hardware.SensorManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private var torchOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toggleTorch(view: android.view.View) {
        // 1. access camera manager through API
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager



        // 2. find a camera that has a flashlight
        for(id in cameraManager.cameraIdList) {
            // if the camera has a flashlight, set the torch on
            if (cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true) {
                torchOn = !torchOn
                cameraManager.setTorchMode(id, torchOn)
            }
        }

        val background = findViewById<ConstraintLayout>(R.id.backgroundConstraintLayout)
        if (torchOn) {
            // set layout background
            background.setBackgroundColor(Color.YELLOW)
        } else {
            background.setBackgroundColor(Color.BLACK)
        }

        // access level manager
        //val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        // access device location
        //val locationService = getSystemService(LOCATION_SERVICE) as LocationManager
    }
}