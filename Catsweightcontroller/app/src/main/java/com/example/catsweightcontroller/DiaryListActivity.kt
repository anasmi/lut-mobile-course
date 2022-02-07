package com.example.catsweightcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class DiaryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_list)

        val listView = findViewById<RecyclerView>(R.id.diaryEntryList)
        listView.layoutManager = LinearLayoutManager(this)

        val catsFile = File(this.filesDir,R.string.weights_diary_fileName.toString())
        if(catsFile.exists()) {
            val lineList = mutableListOf<String>()
            catsFile.useLines { lines -> lines.forEach { lineList.add(it) }}
            listView.adapter = WeightRecyclerViewAdapter(this, lineList)
        }
    }
}