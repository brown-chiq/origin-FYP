package com.example.fyp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.example.fyp.R.id.back_button
import com.example.fyp.R.id.intro_button
import com.example.fyp.R.layout.activity_crash_course_main

class CrashCourseMain : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_crash_course_main)

        var introButton = findViewById<Button>(intro_button)
        introButton.setOnClickListener {
            val intent = Intent(this, CrashCourseIntroTopic::class.java)
            startActivity(intent)
        }

        var backButton = findViewById<ImageView>(back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }
}