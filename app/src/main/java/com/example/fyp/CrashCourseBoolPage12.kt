package com.example.fyp

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.ComponentActivity

class CrashCourseBoolPage12 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash_course_bool_page12)
        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, CrashCourseMain::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        var nextButton = findViewById<Button>(R.id.next_page)
        nextButton.setOnClickListener {
            val intent = Intent(this, CrashCourseBoolPage13::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var prevButton = findViewById<Button>(R.id.prev_page)
        prevButton.setOnClickListener {
            val intent = Intent(this, CrashCourseBoolPage11::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        class ProgressBarAnimation(
            private val progressBar: ProgressBar,
            private val from: Float,
            private val to: Float
        ) :
            Animation() {
            protected override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                super.applyTransformation(interpolatedTime, t)
                val value = from + (to - from) * interpolatedTime
                progressBar.progress = value.toInt()
            }
        }
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val anim = ProgressBarAnimation(progressBar, 84F, 90F)
        anim.duration = 1000
        progressBar.startAnimation(anim)


    }
}