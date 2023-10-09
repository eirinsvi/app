package com.example.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

class RandomNumberActivity : Activity() {
    var generatedNumber = 0
    var upperLimit = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)
        upperLimit = intent.getIntExtra("upperLimit", upperLimit)
        onClickGenerateNumber()
    }

    fun onClickGenerateNumber(){
        generatedNumber = (0..upperLimit).random()
        Toast.makeText(this, generatedNumber.toString(), Toast.LENGTH_LONG).show()
        onClickEndRandomNumberActivity()
    }

    fun onClickEndRandomNumberActivity() {
        setResult(RESULT_OK, Intent().putExtra("number", generatedNumber))
        finish()
    }
}