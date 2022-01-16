package com.example.myapplication

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import layout.Item
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.*

class ItemAdapter(private val mContext: Context, private val itemList: MutableList<Item>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descView: TextView = view.findViewById(R.id.cardDesc)
        val tempView: TextView = view.findViewById(R.id.text_temp)
        val dayView: TextView = view.findViewById(R.id.text_day)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycle_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        var item: Item = itemList[position]

        val imgURL: String = item.getImgUrl()
        val temp: String = item.getTemp()
        val desc: String = item.getDay() //refactored to description instead of day

        // determine day
        val dow = Calendar.DAY_OF_WEEK
        var dayName: String = ""

        if(position == 0) {
            dayName = "Today"
        }
        else if(position == 1) {
            dayName = "Tomorrow"
        }
        else {
            when ((dow - 1 + position) % 7) {
                0 -> dayName = "Monday"
                1 -> dayName = "Tuesday"
                2 -> dayName = "Wednesday"
                3 -> dayName = "Thursday"
                4 -> dayName = "Friday"
                5 -> dayName = "Saturday"
                6 -> dayName = "Sunday"
                else -> dayName = "HUH"
            }
        }


        //holder.imgUrlView.setI
        holder.tempView.text = temp+ "°C" //add more to string to make more clean
        holder.dayView.text = dayName //day
        holder.descView.text = desc //description not renamed


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = itemList.size


}