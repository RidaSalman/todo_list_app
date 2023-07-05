package com.example.to_dolist.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import com.example.to_dolist.Helperclasses.FeaturedHelperClass1
import com.example.to_dolist.R

class FeaturedAdapter1(private val featuredLocations: ArrayList<FeaturedHelperClass1>) : RecyclerView.Adapter<FeaturedAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlist, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = featuredLocations[position]
        // Bind the data to the views in ViewHolder
        holder.description1.text=item.description1
        holder.title1.text=item.title1
        holder.image1.setImageDrawable(item.image1)
        holder.main1.setCardBackgroundColor(item.color1)

        if (position == 1) {
            holder.title1.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.Completetext))
        }
        if (position == 1) {
            holder.description1.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.grey1))
        }

    }

    override fun getItemCount(): Int {
        return featuredLocations.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define your views here
        var description1 : TextView = itemView.findViewById(R.id.description1)
        var title1 : TextView = itemView.findViewById(R.id.title1)
        var image1 : ImageView = itemView.findViewById(R.id.imageView2011)
        var main1 : CardView = itemView.findViewById(R.id.main1)


    }
}
