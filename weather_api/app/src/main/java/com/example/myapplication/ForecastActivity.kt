package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import layout.Item
import org.json.JSONObject
import org.json.JSONException
import com.android.volley.toolbox.JsonObjectRequest


class ForecastActivity : AppCompatActivity() {
    private lateinit var recycleView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemList: MutableList<Item>
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        recycleView = findViewById(R.id.recycler_view)
        recycleView.setHasFixedSize(true) //increased performance with known size

        //itemList = new Array<Item>

        requestQueue = Volley.newRequestQueue(this)

        parseJSON()
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
                        val weatherObj: JSONObject = forecast.getJSONObject("weather")
                        val imgUrl: String = "http://openweathermap.org/img/wn/" +
                                weatherObj.getInt("id").toString() + "@2x.png"
                        val desc: String = forecast.getString("description")

                        //display the formatted json data in text view
                        itemList.add(Item(temperature, desc, imgUrl))
                    }

                }catch (e: JSONException){
                    textView.text = e.message
                }

                progressBar.visibility = View.INVISIBLE
            },
            { error -> // error listener
                textView.text = error.message
                progressBar.visibility = View.INVISIBLE
            }
        )
    }
}