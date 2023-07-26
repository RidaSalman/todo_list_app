package com.example.to_dolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Database.SubTask
import com.example.to_dolist.R

class SubTaskAdapter(private val subTaskList: List<SubTask> ): RecyclerView.Adapter<SubTaskAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_task,parent,false)
        return SubTaskAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubTaskAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return subTaskList.size
    }
}