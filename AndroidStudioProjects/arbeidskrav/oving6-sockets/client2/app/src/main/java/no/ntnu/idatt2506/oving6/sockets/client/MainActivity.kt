package no.ntnu.idatt2506.oving6.sockets.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var client: Client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
        client = Client(textView)
        client.start()
    }

    fun sendMessage(v: View){
        val message = findViewById<EditText>(R.id.editText)
        client.sendToServer(message.text.toString())
    }
}