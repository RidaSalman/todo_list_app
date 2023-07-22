package com.example.to_dolist.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.*
import com.example.to_dolist.Database.Task



class FeaturedAdapter(private val dataList: List<Task>, private val taskDeleteListener: TaskDeleteListener) :
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
        private val textViewDate: TextView = itemView.findViewById(R.id.textViewDate1)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewClipboard1)
        private val cardView: CardView = itemView.findViewById(R.id.mainBackground)
        private val imageButtonMore: ImageButton = itemView.findViewById(R.id.imageButtonMore1)
        private var lastRandomColorIndex: Int? = null

        fun bindThirdView(data: Task) {
            textView.text = data.title
            textViewDate.text = data.date

            val randomColorsArray = itemView.resources.obtainTypedArray(R.array.random_colors)

            // Get a random index for the color
            var randomIndex = (0 until randomColorsArray.length()).random()

            // Ensure the next color is different from the last one
            if (randomColorsArray.length() > 1) {
                while (randomIndex == lastRandomColorIndex) {
                    randomIndex = (0 until randomColorsArray.length()).random()
                }
            }

            // Get the random color resource from the array
            val randomColor = randomColorsArray.getResourceId(randomIndex, 0)

            // Set the random color as the background tint for the ImageView
            imageView.setBackgroundResource(R.drawable.background_circle)
            imageView.background.setTint(ContextCompat.getColor(itemView.context, randomColor))

            // Update the lastRandomColorIndex
            lastRandomColorIndex = randomIndex
            randomColorsArray.recycle()


            val colors = itemView.resources.getIntArray(R.array.card_colors)
            val dataIndex = adapterPosition - 2
            val color = colors[dataIndex % colors.size]
            cardView.setCardBackgroundColor(color)

            imageButtonMore.setOnClickListener {
                showPopupMenu(data)
            }

        }

        private fun showPopupMenu(task: Task) {
            val popupMenu = PopupMenu(imageButtonMore.context, imageButtonMore)
            popupMenu.inflate(R.menu.popup_menu_options)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_update -> {
                        // Handle "Update" option
                        // Call a function to perform the update operation
                        handleUpdateTask(task)

                        true
                    }
                    R.id.menu_delete -> {
                        // Handle "Delete" option
                        // Call a function to perform the delete operation
                        handleDeleteTask(task)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
        private fun handleUpdateTask(task: Task) {
            // Implement the logic to update the task here
            // For example, you can launch the AddNew activity with the task details pre-filled for editing
            val context = imageButtonMore.context
            val intent = Intent(context, AddNew::class.java)
            intent.putExtra("taskId", task.id) // Pass the task ID or relevant data to the AddNew activity
            context.startActivity(intent)
        }

        private fun handleDeleteTask(task: Task) {
            val alertDialogBuilder = AlertDialog.Builder(imageButtonMore.context)
            alertDialogBuilder.apply {
                setTitle("Delete Task")
                setMessage("Are you sure you want to delete this task?")
                setPositiveButton("Yes") { _, _ ->
                    // User clicked the "Yes" button, proceed with the deletion
                    taskDeleteListener.onDeleteTask(task) // Call onDeleteTask of the TaskDeleteListener
                }
                setNegativeButton("No") { dialog, _ ->
                    // User clicked the "No" button, dismiss the dialog
                    dialog.dismiss()
                }
                create().show()
            }
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
        val intent = Intent(context, AddNew::class.java)
        context.startActivity(intent)
    }
    interface TaskDeleteListener {
        fun onDeleteTask(task: Task)
    }
}