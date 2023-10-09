package no.ntnu.idatt2506.oving7storage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import no.ntnu.idatt2506.oving7storage.databinding.ActivityMainBinding
import no.ntnu.idatt2506.oving7storage.managers.FileManager
import no.ntnu.idatt2506.oving7storage.managers.MyPreferenceManager
import no.ntnu.idatt2506.oving7storage.service.Database

class MainActivity : AppCompatActivity() {
    lateinit var mainActivity: ActivityMainBinding
    private lateinit var myPreferenceManager: MyPreferenceManager
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        database = Database(this)
        myPreferenceManager = MyPreferenceManager(this)
        myPreferenceManager.updateBackgroundColor()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        menu.add(0, 1, 0, resources.getString(R.string.option_all_movies))
        menu.add(0, 2, 0, resources.getString(R.string.option_all_movies_with_info))
        menu.add(0, 3, 0, resources.getString(R.string.option_all_directors))
        menu.add(0, 4, 0, resources.getString(R.string.option_all_actors))
        menu.add(0, 5, 0, resources.getString(R.string.option_movies_by_driector))

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> startActivity(Intent("no.ntnu.idatt2506.oving7storage.SettingsActivity"))
            1             -> display(database.allMovies, resources.getString(R.string.option_all_movies))
            2             -> getAllInfoForMovies(database.allMovies, resources.getString(R.string.option_all_movies_with_info))
            3             -> display(database.allDirectors, resources.getString(R.string.option_all_directors))
            4             -> display(database.allActors, resources.getString(R.string.option_all_actors))
            5             -> display(database.getMoviesByDirector("Gary Ross"), resources.getString(R.string.option_movies_by_driector))
            else          -> return false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun display(list: ArrayList<String>, title: String) {
        var result = ""
        for (item in list) result += item+ "\n"
        mainActivity.textView.text = result
        mainActivity.textView2.text = title
    }

    private fun getAllInfoForMovies(listOfMovies: ArrayList<String>, title: String) {
        var result = ""
        for (movie in listOfMovies){
            var listOfActors: ArrayList<String> = database.getActorsByMovie(movie.dropLast(1))
            var listOfDirectors: ArrayList<String> = database.getDirectorsByMovie(movie.dropLast(1))
            result += "$movie, director: " + listOfDirectors.joinToString() + ", actors: " + listOfActors.joinToString() + "\n"
        }
        mainActivity.textView.text = result
        mainActivity.textView2.text = title
    }

    override fun onResume() {
        super.onResume()
        val color = myPreferenceManager.getString(resources.getString(R.string.background_color), resources.getString(R.string.background_color_default_value))
        setBackgroundColor(color)
    }

    fun setBackgroundColor(color: String) {
        var colorInt: Int = ContextCompat.getColor(this, R.color.white)
        when(color) {
            "white" -> colorInt = ContextCompat.getColor(this, R.color.white)
            "pink" -> colorInt = ContextCompat.getColor(this, R.color.background_color_pink)
            "blue" -> colorInt = ContextCompat.getColor(this, R.color.background_color_blue)
        }
        mainActivity.content.setBackgroundColor(colorInt)
    }
}