package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.ComponentActivity

class CrashCourseLogicTopic1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash_course_logic_topic1)

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, CrashCourseMain::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        var nextButton = findViewById<Button>(R.id.next_page)
        nextButton.setOnClickListener {
            val intent = Intent(this, CrashCourseLogicTopic2::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
        val anim = ProgressBarAnimation(progressBar, 0F, 20F)
        anim.duration = 1000
        progressBar.startAnimation(anim)
    }
}