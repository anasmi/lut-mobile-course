package com.example.listapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var listView: RecyclerView
    lateinit var items: Array<String>
    lateinit var descriptions: Array<String>
    lateinit var prices: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.myListView)
        items = resources.getStringArray(R.array.items)
        descriptions = resources.getStringArray(R.array.descriptions)
        prices = resources.getStringArray(R.array.prices)


        listView.adapter = CustomAdapter(this, items, descriptions, prices)
        listView.layoutManager = LinearLayoutManager(this)
    }
}