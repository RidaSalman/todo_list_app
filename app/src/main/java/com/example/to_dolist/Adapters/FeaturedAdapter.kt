package com.example.to_dolist.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import com.example.to_dolist.R

class FeaturedAdapter(private val featuredLocations: ArrayList<FeaturedHelperClass>) : RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemgrid, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = featuredLocations[position]
        // Bind the data to the views in ViewHolder
        holder.description.text=item.description
        holder.title.text=item.title
        holder.image.setImageDrawable(item.image)
        holder.main.setCardBackgroundColor(item.color)

        if (position == 1){
            holder.title.setTextColor(R.color.opaque1)
        }

    }

    override fun getItemCount(): Int {
        return featuredLocations.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define your views here
        var description : TextView = itemView.findViewById(R.id.description)
        var title : TextView = itemView.findViewById(R.id.title)
        var image : ImageView = itemView.findViewById(R.id.imageView201)
        var main : CardView = itemView.findViewById(R.id.main)


    }
}
