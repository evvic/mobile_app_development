package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateBmi(view: View) {
        val weight = findViewById<EditText>(R.id.editWeight).text.toString().toFloat();
        val height = findViewById<EditText>(R.id.editHeight).text.toString().toFloat();

        val BMI = weight / (height / 100 * height / 100);

        findViewById<TextView>(R.id.bmiResult).text = BMI.toString()
    }
}