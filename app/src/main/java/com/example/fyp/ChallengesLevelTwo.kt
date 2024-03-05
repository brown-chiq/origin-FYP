package com.example.fyp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat


class ChallengesLevelTwo : ComponentActivity() , View.OnClickListener {

    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<QuestionLvl2>? = null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int = 0

    private lateinit var opt1: TextView
    private lateinit var opt2: TextView
    private lateinit var opt3: TextView
    private lateinit var opt4: TextView
    private lateinit var submitbtn: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges_level2_layout)

        opt1 = findViewById(R.id.option_one)
        opt2 = findViewById(R.id.option_two)
        opt3 = findViewById(R.id.option_three)
        opt4 = findViewById(R.id.option_four)
        submitbtn = findViewById(R.id.submit_button)

        mQuestionsList = ConstantsLevelTwo.getQuestions()

        setQuestion()

        opt1.setOnClickListener(this)
        opt2.setOnClickListener(this)
        opt3.setOnClickListener(this)
        opt4.setOnClickListener(this)
        submitbtn.setOnClickListener(this)

    }

    private fun setQuestion(){

        val question = mQuestionsList!![mCurrentPosition -1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            submitbtn.text = "SUBMIT"

        }else{
            submitbtn.text = "SUBMIT"
        }

        var lvlProgressBar = findViewById<ProgressBar>(R.id.lvl_progress_bar)
        lvlProgressBar.progress = mCurrentPosition
        lvlProgressBar.max = mQuestionsList!!.size
        var lvlProgress = findViewById<TextView>(R.id.lvl_progress)
        lvlProgress.text = "$mCurrentPosition" + "/" +mQuestionsList!!.size

        var qnStatement = findViewById<TextView>(R.id.quesion)
        qnStatement.text = question!! .questions

        opt1.text = question.optionOne
        opt2.text = question.optionTwo
        opt3.text = question.optionThree
        opt4.text = question.optionFour
    }

    private fun defaultOptionsView(){

        val options = ArrayList<TextView>()
        options.add(0, opt1)
        options.add(1, opt2)
        options.add(2, opt3)
        options.add(3, opt4)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {

        when (v?.id){
            R.id.option_one ->{
                selectedOptionView(opt1,1)
            }
            R.id.option_two ->{
                selectedOptionView(opt2,2)
            }
            R.id.option_three ->{
                selectedOptionView(opt3,3)
            }
            R.id.option_four ->{
                selectedOptionView(opt4,4)
            }
            R.id.submit_button ->{
                if (mSelectedOptionPosition ==0){ //when selected option is at default = have not selected anything
                    mCurrentPosition ++ // go to next qn

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }else ->{
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(ConstantsLevelTwo.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(ConstantsLevelTwo.TOTAL_QUESTIONS,mQuestionsList!!.size)

//                            if (mCorrectAnswers >= 5){ChallengesMain.}
//                        else{intent.putExtra(ConstantsLevelTwo.PASS_QUIZ,false)}
                            startActivity(intent)
                            Toast.makeText(this,
                                "You have successfully completed this challenge",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{//user selected an option
                    val question = mQuestionsList?.get(mCurrentPosition -1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){ //not correct
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg,"#FFFFFFFF")
                    }else{//correctly answered
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg,"#FFFFFFFF")


                    if(mCurrentPosition == mQuestionsList!!.size){
                        submitbtn.text = "FINISH"
                    }else{
                        submitbtn.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer : Int,drawableView: Int, setTextColor: String) {
        when (answer) {
            1 -> {
                opt1.background = ContextCompat.getDrawable(this, drawableView)
                opt1.setTextColor(Color.parseColor(setTextColor))
            }

            2 -> {
                opt2.background = ContextCompat.getDrawable(this, drawableView)
                opt2.setTextColor(Color.parseColor(setTextColor))
            }

            3 -> {
                opt3.background = ContextCompat.getDrawable(this, drawableView)
                opt3.setTextColor(Color.parseColor(setTextColor))
            }

            4 -> {
                opt4.background = ContextCompat.getDrawable(this, drawableView)
                opt4.setTextColor(Color.parseColor(setTextColor))
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum //new selected option number
        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }

}