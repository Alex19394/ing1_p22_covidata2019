package com.example.myapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AlphabetIndexer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_mystery.*

class MysteryActivity : AppCompatActivity() {

    var quizs = ArrayList<Quizz>()
    var goodaws : Int = 0
    var current_index : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mystery)

        quizs.add(Quizz("In which city or town did the Covid 19 appear for the first time ?", "Hangzhou", "Tianjin", "Wuhan", "Huanggang",3))
        quizs.add(Quizz("How is COVID-19 passed on?", "Through droplets that come from your mouth and nose when you cough or breathe out", "\n" +
                "In sexual fluids, including semen, vaginal fluids or anal mucous", "By drinking unclean water", "All of the above", 1))
        quizs.add(Quizz("What are the common symptoms of COVID-19?", "A new and continuous cough", "Fever", "Tiredness", "All of the above", 4))
        quizs.add(Quizz("Can washing your hands protect you from COVID-19?", "Yes – but only if you use a strong bleach", "Yes – normal soap and water or hand sanitizer is enough", "No – Washing your hands doesn’t stop COVID-19", "D la reponse d", 2))
        quizs.add(Quizz("When should fabric face masks be worn?", "On public transport", "In confined or crowded spaces", "In small shops", "All of the above", 4))

        showsQuestion(quizs.get(current_index))
    }

    fun showsQuestion (quizz: Quizz) {
        txtquestion.setText(quizz.question)
        answer1.setText(quizz.answer1)
        answer2.setText(quizz.answer2)
        answer3.setText(quizz.answer3)
        answer4.setText(quizz.answer4)
    }

    fun handleAnswer(answerID: Int){
        val quiz = quizs.get(current_index)
        if (quiz.isCorrect(answerID)){
            goodaws++
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
        }
        current_index++

        if (current_index >= quizs.size) {
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Mystery challenge completed")
            alert.setMessage("You have " + goodaws + "good answer in this mystery challenge")
            alert.setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                finish()
            }
            alert.show()
        } else {
            showsQuestion(quizs.get(current_index))
        }
    }
    fun onClickAnswerOne (view: View) {
        handleAnswer(1)
    }

    fun onClickAnswerTwo (view: View) {
        handleAnswer(2)
    }

    fun onClickAnswerThree (view: View) {
        handleAnswer(3)
    }

    fun onClickAnswerFour (view: View) {
        handleAnswer(4)
    }
}