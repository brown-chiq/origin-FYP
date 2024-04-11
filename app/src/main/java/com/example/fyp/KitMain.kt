package com.example.fyp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity

class KitMain : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kit_main)

        var userGuide = findViewById<Button>(R.id.manual)
        userGuide.setOnClickListener {
            val intent = Intent(this, UserGuide::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


        var logikitInput = findViewById<Button>(R.id.logikit_input_set)
        logikitInput.setOnClickListener {
            val intent = Intent(this, InputSelection::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var truthtableGen = findViewById<Button>(R.id.truthtable_gen)
        truthtableGen.setOnClickListener {
            val intent = Intent(this, LogiKitTruthTable::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


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