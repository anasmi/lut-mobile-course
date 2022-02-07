package com.example.catsweightcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class AllPetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_pets)

        val allPetsList = findViewById<RecyclerView>(R.id.allPetsList)
        allPetsList.layoutManager = LinearLayoutManager(this)

        val catsFile = File(this.filesDir, R.string.cats_file_name.toString())
        if (catsFile.exists()) {
            val lineList = mutableListOf<String>()
            catsFile.useLines { lines -> lines.forEach { lineList.add(it) } }
            allPetsList.adapter = CatRecyclerViewAdapter(lineList)
        }
        /*
        allPetsList.layoutManager = LinearLayoutManager(this)

        val cats = intent.extras?.getStringArrayList("catsNames")
        if(!cats.isNullOrEmpty()) {
            allPetsList.adapter = CatRecyclerViewAdapter(this, cats)
        }*/

    }
}