package com.example.catsweightcontroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.io.File

/**
 * Main view. Acts as a navigation for the app.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val catsFile = File(this.filesDir, R.string.cats_file_name.toString())
        if (!catsFile.exists()) {
            catsFile.createNewFile()
        }
        val addWeightEntryButton = findViewById<Button>(R.id.addWeightEntry)
        addWeightEntryButton.setOnClickListener {
            val addWeightEntry = Intent(this, AddWeightEntry::class.java)
            addWeightEntry.putExtra("catsNames", getCats())
            startActivity(addWeightEntry)
        }

        val addCat = findViewById<Button>(R.id.addCatButton)
        addCat.setOnClickListener {
            val toAddCat = Intent(this, AddCatActivity::class.java)
            startActivity(toAddCat)
        }

        val showListButton = findViewById<Button>(R.id.viewRecordsButton)
        showListButton.setOnClickListener {
            val viewDiary = Intent(this, DiaryListActivity::class.java)
            startActivity(viewDiary)
        }

        val yourPetsButton = findViewById<Button>(R.id.yourPetsButton)
        yourPetsButton.setOnClickListener {
            val allPetsActivity = Intent(this, AllPetsActivity::class.java)
            allPetsActivity.putExtra("catsNames", getCats())
            startActivity(allPetsActivity)
        }
    }

    private fun getCats(): ArrayList<String> {
        val list = ArrayList<String>()
        val catsFile = File(this.filesDir, R.string.cats_file_name.toString())
        list.addAll(catsFile.readText().split("\n"))
        return list
    }
}