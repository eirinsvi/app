package no.ntnu.idatt2506.oving5.http

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val URL = " https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"
class MainActivity : Activity() {
    private val requestCodeLogin = 1
    private var name = ""
    private var card = ""
    private var number = ""
    private val network: HttpWrapper = HttpWrapper(URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login(null)
    }

    fun guessNumber(view: View?) {
        number = findViewById<EditText>(R.id.number).text.toString()
        if (number.isBlank()){
            Toast.makeText(this, "Please input number!", Toast.LENGTH_SHORT).show()
            return
        }
        performRequest(HTTP.GET, mapOf("tall" to number))
    }

    private fun login(view: View?){
        val intent = Intent("no.ntnu.idatt2506.oving5.http.LoginActivity")
        startActivityForResult(intent, requestCodeLogin)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }
        if(requestCodeLogin == requestCode){
            name = data.getStringExtra("name").toString()
            card = data.getStringExtra("card").toString()

            performRequest(HTTP.GET, requestParameters())
            return
        }

    }
    /**
     * Create a map with parameters for HTTP requests
     */
    private fun requestParameters(): Map<String, String> {
        return mapOf(
            "navn" to name,
            "kortnummer" to card,
        )
    }
    private fun performRequest(typeOfRequest: HTTP, parameterList: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> network.get(parameterList)
                    HTTP.POST -> network.post(parameterList)
                    HTTP.GET_WITH_HEADER -> network.getWithHeader(parameterList)
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }

            // Endre UI på hovedtråden
            MainScope().launch {
                setResult(response)
            }
        }
    }
    private fun setResult(response: String?) {
        findViewById<TextView>(R.id.response).text = response
    }
}