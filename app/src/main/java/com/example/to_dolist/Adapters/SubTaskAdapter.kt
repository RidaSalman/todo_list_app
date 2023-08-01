// SubTaskAdapter.kt
package com.example.to_dolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Database.SubTask
import com.example.to_dolist.R

class SubTaskAdapter(var subTaskList: List<SubTask>) :
    RecyclerView.Adapter<SubTaskAdapter.SubTaskViewHolder>() {

    inner class SubTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subTaskTitleTextView: TextView = itemView.findViewById(R.id.textViewTitle11)

        fun bind(subTask: SubTask) {
            subTaskTitleTextView.text = subTask.subTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_subtask, parent, false)
        return SubTaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubTaskViewHolder, position: Int) {
        val currentSubTask = subTaskList[position]
        holder.bind(currentSubTask)
    }

    override fun getItemCount() = subTaskList.size

    // Update the visibility of subTaskList to internal (or public)
    internal fun updateSubTasks(newSubTasks: List<SubTask>) {
        subTaskList = newSubTasks
        notifyDataSetChanged()
    }
}
