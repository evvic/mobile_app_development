package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var myReceiver: BroadcastReceiver
    private lateinit var myReceiver2: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myReceiver = MyReceiver()
        // create an intent filter which defines the broadcast
        val powerConnectedFilter = IntentFilter( Intent.ACTION_POWER_CONNECTED )
        // register our BR with the intent filter to get the broadcast
        registerReceiver(myReceiver, powerConnectedFilter)

        // same thing but for disconnecting
        myReceiver2 = MyReceiver()
        val powerDisconnectedFilter = IntentFilter( Intent.ACTION_POWER_DISCONNECTED )
        registerReceiver(myReceiver2, powerDisconnectedFilter)
    }

    // Nested classes have access to member variables (MainActivity UI elements) of it's parent class
    inner class MyReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.action.equals( Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(context, "Gas me up", Toast.LENGTH_SHORT).show()
                val chargerStatus = findViewById<TextView>(R.id.isConnected)
                chargerStatus.text = "Charger connected!"
            }
            if(intent?.action.equals( Intent.ACTION_POWER_DISCONNECTED)) {
                Toast.makeText(context, "Go down & skip town", Toast.LENGTH_SHORT).show()
                val chargerStatus = findViewById<TextView>(R.id.isConnected)
                chargerStatus.text = "Charger disconnected!"
            }
        }
    }

    fun secondActivity(view: android.view.View) {
        // clicking this button should take the user to the lotto activity
        val intent = Intent(this, Lotto::class.java)
        startActivity(intent)
    }
}