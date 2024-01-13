package com.example.fyp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class ChallengesLevelOneQ1 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges_level_one_q1)

        val questionsList = Constants.getQuestions()
        Log.i("Questions Size", "${questionsList.size}")

        val currentPosition = 1
        val question: Question? = questionsList[currentPosition -1]

        var lvlProgressBar = findViewById<ProgressBar>(R.id.lvl_progress_bar)
        lvlProgressBar.progress = currentPosition
        var lvlProgress = findViewById<TextView>(R.id.lvl_progress)
        lvlProgress.text = "$currentPosition" + "/" +lvlProgressBar.max

        var qnStatement = findViewById<TextView>(R.id.quesion)
        qnStatement.text = question!! .questions
        var qnImage = findViewById<ImageView>(R.id.qn_image)
        qnImage.setImageResource(question.image)

        var opt1 = findViewById<TextView>(R.id.option_one)
        opt1.text = question.optionOne
        var opt2 = findViewById<TextView>(R.id.option_two)
        opt2.text = question.optionTwo
        var opt3 = findViewById<TextView>(R.id.option_three)
        opt3.text = question.optionThree
        var opt4 = findViewById<TextView>(R.id.option_four)
        opt4.text = question.optionFour

    }
}