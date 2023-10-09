package no.ntnu.idatt2506.oving3.friends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditFriendActivity : AppCompatActivity() {
    private val numberRequestCode: Int = 1
    private var firstName = ""
    private var lastName = ""
    private var birthDate = ""
    private var isEdit = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_friend)

        var button  = findViewById<View>(R.id.button) as Button
        var header  = findViewById<View>(R.id.textView) as TextView

        if(intent.getStringExtra("action").toString() == "add"){
            isEdit = false
            button.text = getString(R.string.add_button)
            header.text = getString(R.string.add_header)
        } else if (intent.getStringExtra("action").toString() == "edit"){
            isEdit = true

            button.text = getString(R.string.edit_button)
            header.text = getString(R.string.edit_header)

            firstName = intent.getStringExtra("firstname").toString()
            lastName = intent.getStringExtra("lastname").toString()
            birthDate = intent.getStringExtra("birthdate").toString()
            var fName  = findViewById<View>(R.id.editFirstName) as EditText
            fName.hint = firstName
            var lName  = findViewById<View>(R.id.editLastName) as EditText
            lName.hint = lastName
            var birthD  = findViewById<View>(R.id.editBirthDate) as EditText
            birthD.hint = birthDate
        }
    }

    fun onClickUpdateFriend(v: View?) {
        var fName  = (findViewById<View>(R.id.editFirstName) as EditText).text.toString()
        var lName  = (findViewById<View>(R.id.editLastName) as EditText).text.toString()
        var birthD  = (findViewById<View>(R.id.editBirthDate) as EditText).text.toString()

        Log.e("nameisnull", fName)
        Log.e("nameisnull", lName)
        Log.e("nameisnull", birthD)


        if(!isEdit && (fName.isEmpty() || lName.isEmpty() || birthD.isEmpty())){
            Toast.makeText(this, "All fields must be filled out", Toast.LENGTH_LONG).show()
            return
        }
        if (isEdit){
            if(fName.isEmpty()) fName = firstName
            if(lName.isEmpty()) lName = lastName
            if(birthD.isEmpty()) birthD = birthDate
        }

        setResult(RESULT_OK, Intent()
            .putExtra("firstname", fName)
            .putExtra("lastname", lName)
            .putExtra("birthdate", birthD)
            .putExtra("action", "edit"))
        finish()
    }
}