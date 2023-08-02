package com.example.to_dolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Models.SubTask
import com.example.to_dolist.R

class SubTaskAdapter(private val taskId: Long,var subTaskList: List<SubTask>, var callBackUpdate: ((SubTask) -> Unit)) :
    RecyclerView.Adapter<SubTaskAdapter.SubTaskViewHolder>() {

    inner class SubTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subTaskTitleTextView: TextView = itemView.findViewById(R.id.textViewTitle11)
        private val isCompleted: CheckBox = itemView.findViewById(R.id.checkBoxTaskCompleted)

        fun bind(subTask: SubTask) {
            subTaskTitleTextView.text = subTask.subTitle
            isCompleted.isChecked = subTask.isComplete

            isCompleted.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    subTask.isComplete = true
                    callBackUpdate.invoke(subTask)
                } else {
                    subTask.isComplete = false
                    callBackUpdate.invoke(subTask)
                }
            }
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

}
