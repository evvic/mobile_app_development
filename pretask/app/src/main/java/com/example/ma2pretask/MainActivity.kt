package com.example.ma2pretask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // import textView component
    // lateinit variable because it will be assigned to textView onCreate
    lateinit var list: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.list = findViewById(R.id.myTextView)

        this.list.text = "Hey what's up hello"

    }


}