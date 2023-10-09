package com.example.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CalculateActivity : Activity() {
    private var upperLimit = 0
    private var answerString = ""
    private var numberOne = 0
    private var numberTwo = 0
    private var count = 0
    private val numberRequestCode: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)
        var firstNumber  = findViewById<View>(R.id.numberOne) as TextView
        numberOne = Integer.parseInt(firstNumber.text.toString())
        var secondNumber  = findViewById<View>(R.id.numberTwo) as TextView
        numberTwo = Integer.parseInt(secondNumber.text.toString())
        Toast.makeText(this, "app started!", Toast.LENGTH_LONG).show()
        generateRandomNumber()
        generateRandomNumber()
    }

    fun generateRandomNumber(){
        var uppLim  = findViewById<View>(R.id.upperLimit) as EditText
        upperLimit = Integer.parseInt(uppLim.text.toString())
        val intent = Intent("com.example.idatt2506.oving2.RandomNumberActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, numberRequestCode)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        if (requestCode == numberRequestCode) {
            if(count == 0){
                numberOne = data.getIntExtra("number", numberOne)
                val numOne =  findViewById<View>(R.id.numberOne) as TextView
                numOne.text = numberOne.toString()
                Log.d("name",numberOne.toString())
                count ++
            } else {
                numberTwo = data.getIntExtra("number", numberTwo)
                val numTwo =  findViewById<View>(R.id.numberTwo) as TextView
                numTwo.text = numberTwo.toString()
                Log.d("name",numberTwo.toString())
                count = 0
            }
        }
    }

    fun onClickAdd(v: View?){
        var userInput  = findViewById<View>(R.id.userAnswer) as EditText
        val userAns: Int = Integer.parseInt(userInput.text.toString())
        val solution = numberOne + numberTwo

        answerString = if(solution == userAns){
            getString(R.string.right)
        } else{
            getString(R.string.wrong) + solution.toString()
        }
        Log.d("name",answerString)
        Toast.makeText(this, answerString, Toast.LENGTH_LONG).show()
        generateRandomNumber()
        generateRandomNumber()
    }

    fun onClickMultiply(v: View?){
        var userInput  = findViewById<View>(R.id.userAnswer) as EditText
        val userAns: Int = Integer.parseInt(userInput.text.toString())
        val solution = numberOne*numberTwo

        answerString = if(solution == userAns){
            getString(R.string.right)
        } else{
            getString(R.string.wrong) + solution.toString()
        }
        Log.d("name",answerString)

        Toast.makeText(this, answerString, Toast.LENGTH_LONG).show()
        generateRandomNumber()
        generateRandomNumber()
    }

}