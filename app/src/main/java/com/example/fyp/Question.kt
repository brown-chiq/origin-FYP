package com.example.fyp

data class Question (

    val id: Int,
    val questions: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int // 1,2,3,4

)