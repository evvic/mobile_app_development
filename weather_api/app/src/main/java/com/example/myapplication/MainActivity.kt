package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var citySearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        citySearch = findViewById(R.id.citySearch)

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

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


}