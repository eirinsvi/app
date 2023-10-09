package no.ntnu.idatt2506.oving4.fragmenter

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), FragmentList.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOrientation(resources.configuration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_prev -> previousPressed()
            R.id.menu_next -> nextPressed()
            else -> return false
        }
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setOrientation(newConfig)
    }
    private fun setOrientation(config: Configuration) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val content = findViewById<GridView>(R.id.content) as LinearLayout
        content.orientation =
            if (config.orientation == Configuration.ORIENTATION_PORTRAIT) LinearLayout.VERTICAL
            else LinearLayout.HORIZONTAL
        transaction.commit()
    }

    private fun nextPressed(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentlist) as FragmentList
        fragment.nextPressed()
    }

    private fun previousPressed(){
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentlist) as FragmentList
        fragment.previousPressed()
    }

    override fun onFragmentInteraction(description: String?, picture: Drawable?) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentDes) as FragmentDescription
        fragment.setDescription(description, picture)
    }

}