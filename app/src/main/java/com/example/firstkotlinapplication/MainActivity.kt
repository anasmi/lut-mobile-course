package com.example.firstkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val firstNumEditText = findViewById<EditText>(R.id.firstNumEditText);
            val secondNumEditText = findViewById<EditText>(R.id.secondNumberTextField);
            val textView = findViewById<TextView>(R.id.resultTextView);

            val num1 =
                if (firstNumEditText.text.isNullOrEmpty()) 0 else Integer.parseInt(firstNumEditText.text.toString());
            val num2 = if (secondNumEditText.text.isNullOrEmpty()) 0 else Integer.parseInt(
                secondNumEditText.text.toString()
            );
            val result = num1 + num2;
            textView.text = result.toString();
        }
    }
}