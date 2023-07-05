package com.example.to_dolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Adapters.FeaturedAdapter
import com.example.to_dolist.Adapters.FeaturedAdapter1
import com.example.to_dolist.Helperclasses.FeaturedHelperClass
import com.example.to_dolist.Helperclasses.FeaturedHelperClass1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Dashboard : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewNew: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbaoad)

        nameTextView = findViewById(R.id.textView3)
        recyclerView = findViewById(R.id.recyclerViewTask)
        recyclerViewNew = findViewById(R.id.recyclerViewNew)

       initRecyclerView()
        initNewRecyclerView()



        val dataStore = applicationContext.dataStore

        lifecycleScope.launch {
            val name = withContext(Dispatchers.IO) {
                dataStore.data.first()[USER_NAME_KEY] ?: ""
            }

            // Display the greeting message with the name
            val greetingMessage = "Hi, $name"
            nameTextView.text = greetingMessage
        }
    }

    private fun initRecyclerView() {

        val data = ArrayList<FeaturedHelperClass>()

        data.add(FeaturedHelperClass(ContextCompat.getDrawable(this,R.drawable.cancel1),"Ongoing","10 Task",ContextCompat.getColor(this,R.color.dark_blue)))
        data.add(FeaturedHelperClass(ContextCompat.getDrawable(this,R.drawable.group33),"Complete","45 Task",ContextCompat.getColor(this,R.color.orange)))
        recyclerView.layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = FeaturedAdapter(data)
    }
    private fun initNewRecyclerView() {

        val data = ArrayList<FeaturedHelperClass1>()
        data.add(FeaturedHelperClass1(ContextCompat.getDrawable(this,R.drawable.cancel1),"Ongoing","10 Task",ContextCompat.getColor(this,R.color.dark_blue)))
        data.add(FeaturedHelperClass1(ContextCompat.getDrawable(this,R.drawable.group33),"Complete","45 Task",ContextCompat.getColor(this,R.color.orange)))
        // Add more items as needed
        recyclerViewNew.layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false)
        recyclerViewNew.adapter = FeaturedAdapter1(data)


    }



}
