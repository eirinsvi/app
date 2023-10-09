package no.ntnu.idatt2506.oving5.http

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class LoginActivity : Activity() {
    private var name = ""
    private var cardNumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun login(v : View?){
        name = findViewById<EditText>(R.id.userInputName).text.toString()
        cardNumber = findViewById<EditText>(R.id.userInputCard).text.toString()

        if(name.isBlank() || cardNumber.isBlank()) {
            Toast.makeText(this, "Please input name and credit card!", Toast.LENGTH_SHORT).show()
            return
        }

        setResult(RESULT_OK, Intent()
            .putExtra("name", name)
            .putExtra("card", cardNumber))
        finish()
    }
}