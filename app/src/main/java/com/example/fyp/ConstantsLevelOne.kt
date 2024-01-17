package com.example.fyp

object ConstantsLevelOne {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val PASS_QUIZ: String = "pass_quiz"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        //level 1 question 1
        val l1q1 = Question(1,"What logic gate is this?", R.drawable.nandgate,
            "NAND",
            "AND",
            "NOR",
            "OR",
            1
        )
        questionList.add(l1q1)

        //level 1 question 2
        val l1q2 = Question(2,"What logic gate is this?", R.drawable.norgate,
            "NAND",
            "AND",
            "NOR",
            "OR",
            3
        )
        questionList.add(l1q2)

        //level 1 question 3
        val l1q3 = Question(3,"What logic gate is this?", R.drawable.xorgate,
            "NAND",
            "NOT",
            "NOR",
            "XOR",
            4
        )
        questionList.add(l1q3)

        //level 1 question 4
        val l1q4 = Question(3,"What logic gate is this?", R.drawable.andgate,
            "NOT",
            "AND",
            "OR",
            "XOR",
            2
        )
        questionList.add(l1q4)

        //level 1 question 5
        val l1q5 = Question(3,"What logic gate is this?", R.drawable.xnorgate,
            "NAND",
            "NOR",
            "XOR",
            "XNOR",
            4
        )
        questionList.add(l1q5)

        //level 1 question 6
        val l1q6 = Question(1,"What logic gate is this?", R.drawable.orgate,
            "OR",
            "AND",
            "NOR",
            "XOR",
            1
        )
        questionList.add(l1q6)

        //level 1 question 7
        val l1q7 = Question(2,"What logic gate is this?", R.drawable.notgate,
            "NAND",
            "NOR",
            "NOT",
            "OR",
            3
        )
        questionList.add(l1q7)

        return questionList
    }
}