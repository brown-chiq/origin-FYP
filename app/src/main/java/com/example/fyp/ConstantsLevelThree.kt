package com.example.fyp

object ConstantsLevelThree {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        //level 3 question 1
        val l3q1 = Question(1,"3rd levellll ", R.drawable.nandgate,
            "NAND",
            "AND",
            "NOR",
            "OR",
            1
        )
        questionList.add(l3q1)

        //level 3 question 2
        val l3q2 = Question(2,"SOMETHING More Difficult", R.drawable.norgate,
            "NAND",
            "AND",
            "NOR",
            "OR",
            3
        )
        questionList.add(l3q2)

        //level 3 question 3
        val l3q3 = Question(3,"SOMETHING More Difficult", R.drawable.xorgate,
            "NAND",
            "NOT",
            "NOR",
            "XOR",
            4
        )
        questionList.add(l3q3)



        return questionList
    }
}