package no.ntnu.idatt2506.oving3.friends

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private val numberRequestCode: Int = 1
    private var people: ArrayList<Friend> = arrayListOf(
        Friend("Eirin", "Svinsås", "29.06.01"),
        Friend("Thadsha", "Paramsothy", "22.02.01")
    )
    private var index: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
    }

    fun onClickAddFriend(v: View?) {
        val intent = Intent("no.ntnu.idatt2506.oving3.friends.EditFriendActivity")
        intent.putExtra("action", "add")
        startActivityForResult(intent, numberRequestCode)
    }

    private fun initList() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, people)
        val listView = findViewById<ListView>(R.id.list)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _: AdapterView<*>?, _: View, posisjon: Int, id: Long ->
                index = posisjon
                val intent = Intent("no.ntnu.idatt2506.oving3.friends.EditFriendActivity")
                intent.putExtra("action", "edit")
                intent.putExtra("index", posisjon)
                intent.putExtra("firstname", people[posisjon].firstName)
                intent.putExtra("lastname", people[posisjon].lastName)
                intent.putExtra("birthdate", people[posisjon].birthDate)
                startActivityForResult(intent, numberRequestCode)
            }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != RESULT_OK || requestCode != numberRequestCode) {
            Log.e("onActivityResult()", "Noe gikk galt")
            return
        }
        var firstName = data.getStringExtra("firstname").toString()
        var lastName = data.getStringExtra("lastname").toString()
        var birthDate = data.getStringExtra("birthdate").toString()
        if(index != null){
            people[index!!] = Friend(firstName, lastName, birthDate)
        } else {
            people.add(Friend(firstName, lastName, birthDate))
        }
        initList()
    }
}