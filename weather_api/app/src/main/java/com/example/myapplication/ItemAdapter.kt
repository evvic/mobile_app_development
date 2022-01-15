package com.example.myapplication

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import layout.Item
import com.squareup.picasso.Picasso

class ItemAdapter(private val mContext: Context, private val itemList: Array<Item>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgUrlView: ImageView = view.findViewById(R.id.image_view)
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

        val imgurl: String = item.getImgUrl()
        val temp: String = item.getTemp()
        val day: String = item.getDay()

        //holder.imgUrlView.setI
        holder.tempView.setText(temp+ " C") //add more to string to make more clean
        holder.dayView.setText(day)

        Picasso.get().load(imgurl).fit().centerInside().into(holder.imgUrlView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = itemList.size


}