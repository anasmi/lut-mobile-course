package com.example.quicklauncher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_MESSAGE = "com.example.quicklauncher.extraMessage"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondActivityButton = findViewById<Button>(R.id.secondActivityButton)
        //Launch an activity from within the app
        secondActivityButton.setOnClickListener {
            val startIntentSecondView = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "Hello World!")
            }
            startActivity(startIntentSecondView)
        }

        //Launch an activity from outside the application
        val googleButtonButton = findViewById<Button>(R.id.googleButton)
        googleButtonButton.setOnClickListener {
            val google = "https://www.google.com/"
            val webURI = Uri.parse(google)

            val googleIntent = Intent(Intent.ACTION_VIEW, webURI)
            //Starting from API 30 queries must be included!
            //https://stackoverflow.com/questions/62535856/intent-resolveactivity-returns-null-in-api-30
            if(googleIntent.resolveActivity(packageManager) !=null) {
                startActivity(googleIntent)
            }
        }
    }
}