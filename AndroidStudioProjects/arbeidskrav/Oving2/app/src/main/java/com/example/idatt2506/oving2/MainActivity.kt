package com.example.idatt2506.oving2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : Activity() {
    private val numberRequestCode: Int = 1
    private val upperLimit = 100;
    private var randomNumber = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartRandomNumberActivity(v: View?) {
        val intent = Intent("com.example.idatt2506.oving2.RandomNumberActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, numberRequestCode)
    }

    fun onClickStartCalculateActivity(v: View?) {
        val intent = Intent("com.example.idatt2506.oving2.CalculateActivity")
        intent.putExtra("upperLimit", upperLimit)
        startActivityForResult(intent, numberRequestCode)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        if (requestCode == numberRequestCode) {
            randomNumber = data.getIntExtra("number", randomNumber)
            val generatedNumber = findViewById<View>(R.id.randomNumb) as TextView
            generatedNumber.text = randomNumber.toString()
        }
    }
}