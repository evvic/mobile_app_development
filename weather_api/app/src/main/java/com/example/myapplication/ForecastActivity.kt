package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import layout.Item
import org.json.JSONObject
import org.json.JSONException
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray


class ForecastActivity : AppCompatActivity() {
    private lateinit var recycleView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private var itemList: MutableList<Item> = mutableListOf<Item>()
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        recycleView = findViewById(R.id.recycler_view)
        recycleView.setHasFixedSize(true) //increased performance with known size
        recycleView.layoutManager = LinearLayoutManager(this)

        requestQueue = Volley.newRequestQueue(this)

        parseJSON()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun parseJSON() {
        val city_name = "Sacramento"
        // pass lat and long from main activity when switching to this one
        val lat = "33.44"
        val long = "-94.04"
        //forecast only takes coordinates

        var url: String =
            "https://api.openweathermap.org/data/2.5/onecall?lat=" + lat +
            "&lon="+ long + "&exclude=hourly&appid=6458e55e8621e80acd5796130cc32523"

        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()

        var teststr: String = ""

        val request = JsonObjectRequest(
            Request.Method.GET, // method
            url, // url
            null, // json request
            { response -> // response listener

                try {
                    val obj: JSONObject = response
                    val array = obj.getJSONArray("daily")

                    // loop through the array elements
                    for (i in 0 until  array.length()){
                        // get current json object as daily weather instance
                        val forecast: JSONObject = array.getJSONObject(i)

                        // get the current temperature (json object) data
                        val tempObj: JSONObject = forecast.getJSONObject("temp")
                        // convert Kelvin to celsius and to an int
                        val temperature: Int = (tempObj.getDouble("day") - 273.15).toInt()

                        // get the current weather (json object) data
                        val weatherObj: JSONObject = forecast.getJSONArray("weather").get(0) as JSONObject
                        val imgUrl: String = "http://openweathermap.org/img/wn/" +
                                weatherObj.getString("icon") + ".png"
                        val desc: String = weatherObj.getString("description") ?: "descriptionless"
                        teststr = weatherObj.getString("icon")

                        //display the formatted json data in text view
                        itemList.add(Item(temperature, desc, imgUrl))
                    }

                    Toast.makeText(this, "$"+teststr, Toast.LENGTH_SHORT).show()
                    itemAdapter = ItemAdapter(this, itemList)
                    recycleView.adapter = itemAdapter

                }catch (e: JSONException) {
                    Toast.makeText(this, "-"+e.message, Toast.LENGTH_LONG).show()
                }

            },
            { error -> // error listener
                Toast.makeText(this, "*"+error.message, Toast.LENGTH_LONG).show()
            }
        )

        requestQueue.add(request)
    }

    fun runnit(view: android.view.View) {
        // clicking this button should take the user to the lotto activity
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT)
    }
}