package com.example.to_dolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.to_dolist.R

class CalenderAdapter :RecyclerView.Adapter<CalenderAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_calender,parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 30

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}