package com.example.fyp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity

class ChallengesMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges_main)

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        var levelOne = findViewById<Button>(R.id.level_one)
        levelOne.setOnClickListener {
            val intent = Intent(this, ChallengesLevelOne::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var levelTwo = findViewById<Button>(R.id.level_two)
        levelTwo.setOnClickListener {
            val intent = Intent(this, ChallengesLevelOne::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var levelThree = findViewById<Button>(R.id.level_three)
        levelThree.setOnClickListener {
            val intent = Intent(this, ChallengesLevelOne::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var levelFour = findViewById<Button>(R.id.level_four)
        levelFour.setOnClickListener {
            val intent = Intent(this, ChallengesLevelOne::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}