package com.example.to_dolist.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.CreateTaskFragment
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import com.example.to_dolist.MainActivity
import com.example.to_dolist.R
import com.example.to_dolist.UserInfo

class FeaturedAdapter(private val dataList: ArrayList<FeaturedHelperClass>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Define the view types
    companion object {
        private const val VIEW_TYPE_FIRST = 0
        private const val VIEW_TYPE_SECOND = 1
        private const val VIEW_TYPE_THIRD = 2
    }
    // Implement the necessary methods: getItemCount, getItemViewType, and onCreateViewHolder

    override fun getItemCount(): Int {
        return dataList.size + 2 // Add 2 for the first static view, second static view, and the third view's title
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_FIRST
            1 -> VIEW_TYPE_SECOND
            2 -> VIEW_TYPE_THIRD
            else -> VIEW_TYPE_THIRD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FIRST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_home_header, parent, false)
                FirstViewHolder(view)
            }

            VIEW_TYPE_SECOND -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_section_title, parent, false)
                SecondViewHolder(view)
            }

            VIEW_TYPE_THIRD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_task, parent, false)
                ThirdViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    inner class FirstViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView1: TextView = itemView.findViewById(R.id.textViewOnGoingTaskCount)
        private val textView2: TextView = itemView.findViewById(R.id.textViewCompleteTaskCount)
        private val textView3: TextView = itemView.findViewById(R.id.textViewCancelTaskCount)
        private val cardView: CardView = itemView.findViewById(R.id.cardViewAddNew)


        fun bindFirstView() {
            cardView.setOnClickListener {
                val context = itemView.context
                openCreateTaskScreen(context)
            }

        }

    }
    inner class SecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindSecondView() {
        }
    }

    inner class ThirdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textViewTitle1)
        private val cardView: CardView = itemView.findViewById(R.id.mainBackground)

        fun bindThirdView(data: FeaturedHelperClass) {
            textView.text = data.title
            cardView.setCardBackgroundColor(data.color)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_FIRST -> {
                val firstViewHolder = holder as FirstViewHolder
                firstViewHolder.bindFirstView()
            }

            VIEW_TYPE_SECOND -> {
                val secondViewHolder = holder as SecondViewHolder
                secondViewHolder.bindSecondView()
            }

            VIEW_TYPE_THIRD -> {
                val thirdViewHolder = holder as ThirdViewHolder
                val dataIndex =
                    position - 2 // Adjust the index to account for the first two static views
                if (dataIndex >= 0 && dataIndex < dataList.size) {
                    thirdViewHolder.bindThirdView(dataList[dataIndex])
                }
            }
        }
    }

    private fun openCreateTaskScreen(context: Context) {
        val intent = Intent(context, UserInfo::class.java)
        context.startActivity(intent)
    }
}