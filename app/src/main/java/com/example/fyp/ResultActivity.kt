package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ResultActivity : ComponentActivity() {

    private lateinit var score: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        score = findViewById(R.id.tv_score)
        score.text = "Your Score is $correctAnswers out of $totalQuestions"


        var finishButton = findViewById<Button>(R.id.finish_button)
        finishButton.setOnClickListener {
            val intent = Intent(this, ChallengesMain::class.java)
            startActivity(intent)
        }
    }
}