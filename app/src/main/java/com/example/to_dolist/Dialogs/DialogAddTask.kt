package com.example.to_dolist.Dialogs

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.to_dolist.R

class DialogAddTask(
    context: Context,
    setCancelable: Boolean = true,
    onRightButtonClickListener: (Dialog, String) -> Unit,
    onLeftButtonClickListener: (Dialog) -> Unit
) : Dialog(
    context
) {
    init {
        setContentView(R.layout.subtask_popup)
        this.setCancelable(setCancelable)

        val btnAdd = findViewById<Button>(R.id.add_btn)
        val btnCancel = findViewById<Button>(R.id.cancel_btn)
        val titleText = findViewById<EditText>(R.id.subtask_title)

        btnAdd.setOnClickListener {
            val subtaskTitleEditText = titleText.text.toString().trim()
            if (subtaskTitleEditText.isNotEmpty()) {
                onRightButtonClickListener(this, subtaskTitleEditText)
                dismiss()
            } else {
                Toast.makeText(context, "Please Enter a task title", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            onLeftButtonClickListener(this)
            dismiss()
        }
    }
}
