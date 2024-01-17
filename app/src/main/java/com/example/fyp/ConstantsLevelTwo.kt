package com.example.fyp

object ConstantsLevelTwo {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        //level 2 question 1
        val l2q1 = Question(1,"SOMWTHIN More Difficult", R.drawable.nandgate,
            "NAND",
            "AND",
            "NOR",
            "OR",
            1
        )
        questionList.add(l2q1)

        //level 2 question 2
        val l2q2 = Question(2,"What logic gate is this?", R.drawable.norgate,
            "NAND",
            "AND",
            "NOR",
            "OR",
            3
        )
        questionList.add(l2q2)

        //level 2 question 3
        val l2q3 = Question(3,"What logic gate is this?", R.drawable.xorgate,
            "NAND",
            "NOT",
            "NOR",
            "XOR",
            4
        )
        questionList.add(l2q3)

        //level 2 question 4
        val l2q4 = Question(3,"What logic gate is this?", R.drawable.andgate,
            "NOT",
            "AND",
            "OR",
            "XOR",
            2
        )
        questionList.add(l2q4)

        //level 2 question 5
        val l2q5 = Question(3,"What logic gate is this?", R.drawable.xnorgate,
            "NAND",
            "NOR",
            "XNOR",
            "XOR",
            4
        )
        questionList.add(l2q5)

        //level 2 question 6
        val l2q6 = Question(1,"What logic gate is this?", R.drawable.orgate,
            "OR",
            "AND",
            "NOR",
            "XOR",
            2
        )
        questionList.add(l2q6)

        //level 2 question 7
        val l2q7 = Question(2,"What logic gate is this?", R.drawable.notgate,
            "NAND",
            "NOR",
            "NOT",
            "OR",
            3
        )
        questionList.add(l2q7)

        return questionList
    }
}