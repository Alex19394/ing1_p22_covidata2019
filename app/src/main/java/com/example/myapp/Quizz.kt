package com.example.myapp

class Quizz (var question: String, var answer1: String, var answer2: String, var answer3: String, var answer4: String, var correctAnswer: Int){
    fun isCorrect(answerNumber: Int) : Boolean{
        if (answerNumber == correctAnswer)
            return true
        return false
    }
}