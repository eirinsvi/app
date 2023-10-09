package no.ntnu.idatt2506.oving7storage.service

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import no.ntnu.idatt2506.oving7storage.R
import no.ntnu.idatt2506.oving7storage.managers.DatabaseManager
import no.ntnu.idatt2506.oving7storage.managers.FileManager


class Database(context: Context) : DatabaseManager(context) {

	init {
		try {
			val fileManager = FileManager(context as AppCompatActivity)
			val movies = fileManager.readFileFromResFolder(R.raw.movies)
			fileManager.write(movies, "WriteTo.txt")
			val listOfMovies = movies.split("[\r\n]+".toRegex()).toTypedArray()

			this.clear()
			for(movie in listOfMovies) {
				val movieList = movie.split(",".toRegex()).toTypedArray()
				this.insert(movieList[0], movieList[1], movieList[2], movieList[3])
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	val allMovies: ArrayList<String>
		get() = performQuery(TABLE_MOVIE, arrayOf( MOVIE_NAME))

	val allDirectors: ArrayList<String>
		get() = performQuery(TABLE_DIRECTOR, arrayOf(DIRECTOR_NAME))

	val allActors: ArrayList<String>
		get() = performQuery(TABLE_ACTOR, arrayOf(ACTOR_NAME))

	fun getMoviesByDirector(director: String): ArrayList<String> {
		val select = arrayOf("$TABLE_MOVIE.$MOVIE_NAME")
		val from = arrayOf(TABLE_DIRECTOR, TABLE_MOVIE, TABLE_MOVIE_DIRECTOR)
		val join = JOIN_MOVIE_DIRECTOR
		val where = "$TABLE_DIRECTOR.$DIRECTOR_NAME='$director'"

		return performRawQuery(select, from, join, where)
	}

	fun getActorsByMovie(movie: String): ArrayList<String> {
		val select = arrayOf("$TABLE_ACTOR.$ACTOR_NAME")
		val from = arrayOf(TABLE_ACTOR, TABLE_MOVIE, TABLE_MOVIE_ACTOR)
		val join = JOIN_MOVIE_ACTOR
		val where = "$TABLE_MOVIE.$MOVIE_NAME='$movie'"

		return performRawQuery(select, from, join, where)
	}

	fun getDirectorsByMovie(movie: String): ArrayList<String> {
		val select = arrayOf("$TABLE_DIRECTOR.$DIRECTOR_NAME")
		val from = arrayOf(TABLE_DIRECTOR, TABLE_MOVIE, TABLE_MOVIE_DIRECTOR)
		val join = JOIN_MOVIE_DIRECTOR
		val where = "$TABLE_MOVIE.$MOVIE_NAME='$movie'"

		return performRawQuery(select, from, join, where)
	}
}
