package com.example.quicklauncher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if(intent.hasExtra(EXTRA_MESSAGE)) {
            val textView = findViewById<TextView>(R.id.labelTextView);
            val extraText = intent.getStringExtra(EXTRA_MESSAGE);
            textView.text = extraText
        }
    }
}