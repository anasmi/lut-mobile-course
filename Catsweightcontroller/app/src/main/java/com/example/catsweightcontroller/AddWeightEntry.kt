package com.example.catsweightcontroller

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.io.File
import java.util.*

class AddWeightEntry : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weight_entry)

        val catSelector = findViewById<Spinner>(R.id.catSelectorId)
        catSelector.onItemSelectedListener = this

        val catWeightEntry = findViewById<TextView>(R.id.weightValueTV)
        catWeightEntry.addTextChangedListener{
            catWeightEntry.error = null
        }

        val cats = intent.extras?.getStringArrayList("catsNames")
        if(!cats.isNullOrEmpty()) {
            val catsName = cats.map { cat -> cat.split(";")[0] }
            val adapter = ArrayAdapter( applicationContext, android.R.layout.simple_spinner_dropdown_item, catsName)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            catSelector.adapter  = adapter
        }

        val saveWeight = findViewById<Button>(R.id.saveWeightEntryButton)
        saveWeight.setOnClickListener{
            if (catSelector.selectedItem == null) {
                return@setOnClickListener
            }
            else if(catWeightEntry.text.isEmpty()) {
                catWeightEntry.error = "Value must be set!";
            }
            else {
                val weightsFile = File(this.filesDir,
                    R.string.weights_diary_fileName.toString())
                if(!weightsFile.exists()) {
                    weightsFile.createNewFile()
                }
                val line = catSelector.selectedItem.toString()
                    .plus(";")
                    .plus(catWeightEntry.text)
                    .plus(";")
                    .plus( Date())
                    .plus("\n")
                weightsFile.appendText(line)
                catWeightEntry.text = ""
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
}