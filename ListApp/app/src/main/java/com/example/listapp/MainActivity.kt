package com.example.listapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit  var listView :RecyclerView
    lateinit var items : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.myListView)
        items = resources.getStringArray(R.array.items)

        listView.adapter = CustomAdapter(items)
        listView.layoutManager = LinearLayoutManager(this)
    }
}