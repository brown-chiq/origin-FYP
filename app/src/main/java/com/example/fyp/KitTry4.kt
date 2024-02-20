package com.example.fyp

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.ComponentActivity

class KitTry4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kit_try_page4)

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, KitMain::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        var nextButton = findViewById<Button>(R.id.next_page)
        nextButton.setOnClickListener {
            val intent = Intent(this, KitTry4::class.java)
            startActivity(intent)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        var prevButton = findViewById<Button>(R.id.prev_page)
        prevButton.setOnClickListener {
            val intent = Intent(this, KitTry3::class.java)
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
        val anim = ProgressBarAnimation(progressBar, 60F, 80F)
        anim.duration = 1000
        progressBar.startAnimation(anim)

    }
}