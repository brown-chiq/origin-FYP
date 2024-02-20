package com.example.fyp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity

class KitMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kit_main)

//        var manualButton = findViewById<Button>(R.id.manual_button)
//        manualButton.setOnClickListener {
//            val intent = Intent(this, Manual1::class.java)
//            startActivity(intent)
//            @Suppress("DEPRECATION")
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//        }

        var sampleButton = findViewById<Button>(R.id.sample_button)
        sampleButton.setOnClickListener {
            val intent = Intent(this, KitTry1::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }
}