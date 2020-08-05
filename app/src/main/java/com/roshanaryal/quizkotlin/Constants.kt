package com.roshanaryal.quizkotlin

object Constants {
    fun getQuestion():ArrayList<MyQuestion>{

        val questionList= ArrayList<MyQuestion>()

        val question=MyQuestion(1,"Which country this flag belongs to"
            ,"Nepal","Australia","Argentina","India",1)

        questionList.add(question)
        questionList.add(MyQuestion(1,"sum of 9 and 2","1","11","12","14",2))
        questionList.add(MyQuestion(1,"divide of 9 and 3","1","3","9","4",2))
        questionList.add(MyQuestion(1,"sum of 6 and 9","15","12","1","13",1))
        questionList.add(MyQuestion(1,"subtract  of 9 and 2","1","11","7","14",3))



        return questionList

    }
}