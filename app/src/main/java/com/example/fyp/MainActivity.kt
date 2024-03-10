package com.example.fyp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.fyp.R.id.challenges_button
import com.example.fyp.R.id.crashCourseButton
import com.example.fyp.R.id.logikit_button
import com.example.fyp.R.layout.activity_main
@SuppressLint("MissingPermission")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        PermissionManager(this).requestPermissions()

        val logicKitButton = findViewById<Button>(logikit_button)
        logicKitButton.setOnClickListener {
            val intent = Intent(this, KitMain::class.java)
            startActivity(intent)
        }

        val crashCourseButton = findViewById<Button>(crashCourseButton)
        crashCourseButton.setOnClickListener {
            val intent = Intent(this, CrashCourseMain::class.java)
            startActivity(intent)
        }

        val challengesButton = findViewById<Button>(challenges_button)
        challengesButton.setOnClickListener {
            val intent = Intent(this, ChallengesMain::class.java)
            startActivity(intent)
        }
    }
}

