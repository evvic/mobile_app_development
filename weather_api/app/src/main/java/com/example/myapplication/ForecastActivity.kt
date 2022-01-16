package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
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

    // coordinates fro API call
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var city: String = "Weather"

    // title tag
    private lateinit var titleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // get coordinates passed from main
        val bundle: Bundle? = intent.extras
        latitude = bundle?.getDouble("lat") ?: 0.0
        longitude = bundle?.getDouble("lon") ?: 0.0
        city = bundle?.getString("city") ?: "Weather"

        titleView = findViewById(R.id.title_view)
        titleView.text = city + " forecast"

        recycleView = findViewById(R.id.recycler_view)
        recycleView.setHasFixedSize(true) //increased performance with known size
        recycleView.layoutManager = LinearLayoutManager(this)

        requestQueue = Volley.newRequestQueue(this)

        parseJSON()
    }

    private fun parseJSON() {
        //forecast only takes coordinates

        var url: String =
            "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude +
            "&lon="+ longitude + "&exclude=hourly&appid=6458e55e8621e80acd5796130cc32523"

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

                    //Toast.makeText(this, "$"+teststr, Toast.LENGTH_SHORT).show()
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

}