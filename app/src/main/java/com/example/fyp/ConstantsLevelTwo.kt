package com.example.fyp

object ConstantsLevelTwo {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<QuestionLvl2>{
        val questionList = ArrayList<QuestionLvl2>()

        //level 2 question 1
        val l2q1 = QuestionLvl2(1,"DAC+A(DCA+BA)",
            "C' + D",
            "A' + D'",
            "DAC + AB",
            "AC + BD",
            3
        )
        questionList.add(l2q1)

        //level 2 question 2
        val l2q2 = QuestionLvl2(2,"(CC'D)' + (0'DA)'",
            "1",
            "DC +C'A",
            "AD",
            "C",
            1
        )
        questionList.add(l2q2)

        //level 2 question 3
        val l2q3 = QuestionLvl2(3,"D'0'A'+(DDB)'",
            "D' + A",
            "D' + B'",
            "DB' + A",
            "0",
            2
        )
        questionList.add(l2q3)

        //level 2 question 4
        val l2q4 = QuestionLvl2(4,"C0' + 1 (AD1 + BA)",
            "B + A",
            "C + AD + BA",
            "1",
            "C + D",
            2
        )
        questionList.add(l2q4)

        //level 2 question 5
        val l2q5 = QuestionLvl2(5,"B(AB+0D)D+0'C",
            "ABC",
            "A + C' + D'",
            "D",
            "BAD + C",
            4
        )
        questionList.add(l2q5)

        return questionList
    }
}