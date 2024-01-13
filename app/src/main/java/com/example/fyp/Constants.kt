package com.example.fyp

object Constants {

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        //level 1 question 1
        val l1q1 = Question(1,"What logic gate is this?", R.drawable.nand,
            "NAND",
            "AND",
            "NOR",
            "OR",
            1
        )
        questionList.add(l1q1)

        //level 1 question 2
        val l1q2 = Question(1,"What logic gate is this?", R.drawable.nor,
            "NAND",
            "AND",
            "NOR",
            "OR",
            3
        )
        questionList.add(l1q2)

        //level 1 question 3
        val l1q3 = Question(1,"What logic gate is this?", R.drawable.xor,
            "NAND",
            "NOT",
            "NOR",
            "XOR",
            4
        )
        questionList.add(l1q3)



        return questionList
    }
}