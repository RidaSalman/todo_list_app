package com.example.to_dolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import com.example.to_dolist.R

class FeaturedAdapter(private val dataList: ArrayList<FeaturedHelperClass>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Define the view types
    private val VIEW_TYPE_1 = 1
    private val VIEW_TYPE_2 = 2
    private val VIEW_TYPE_3 = 3

    // Implement the necessary methods: getItemCount, getItemViewType, and onCreateViewHolder

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        return when (position) {
            0 -> VIEW_TYPE_1
            1 -> VIEW_TYPE_2
            else -> VIEW_TYPE_3
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_1 -> {
                val view = inflater.inflate(R.layout.row_item_home_header, parent, false)
                Type1ViewHolder(view)
            }
            VIEW_TYPE_2 -> {
                val view = inflater.inflate(R.layout.row_item_section_title, parent, false)
                Type2ViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.row_item_task, parent, false)
                Type3ViewHolder(view)
            }
        }
    }


    inner class Type1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    inner class Type2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    inner class Type3ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var taskBackground : CardView = itemView.findViewById(R.id.mainBackground)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        when (holder) {
            is Type1ViewHolder -> {
                // Bind data and handle clicks for view type 1
            }
            is Type2ViewHolder -> {
                // Bind data and handle clicks for view type 2
            }
            is Type3ViewHolder -> {

               holder.taskBackground.setCardBackgroundColor(data.color)
            }
        }
    }
}