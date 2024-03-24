package com.example.fyp

object ConstantsLevelThree {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        //level 3 question 1
        val l3q1 = Question(1,"Find the simplest boolean function", R.drawable.q1,
            "C",
            "C'",
            "B",
            "A'",
            1
        )
        questionList.add(l3q1)

        //level 3 question 2
        val l3q2 = Question(2,"Find the simplest boolean function", R.drawable.qn2,
            " A ",
            " B ",
            " B' ",
            " C ",
            3
        )
        questionList.add(l3q2)

        //level 3 question 3
        val l3q3 = Question(3,"Find the simplest boolean function", R.drawable.qn3,
            " B'D' +A'C' ",
            " BD + AC ",
            " 1 ",
            " BD + B'D' ",
            4
        )
        questionList.add(l3q3)

        //level 3 question 4
        val l3q4 = Question(4,"Find the simplest boolean function", R.drawable.qn4,
            "A ",
            "D' ",
            "B ",
            " C'",
            2
        )
        questionList.add(l3q4)



        return questionList
    }
}