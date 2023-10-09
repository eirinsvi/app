package no.ntnu.idatt2506.oving7storage.managers

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import no.ntnu.idatt2506.oving7storage.R

class MyPreferenceManager(private val activity: AppCompatActivity) {

	private val resources = activity.resources
	private val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
	private val editor: SharedPreferences.Editor = preferences.edit()


	fun putString(key: String, value: String) {
		editor.putString(key, value)
		editor.apply()
	}

	fun getString(key: String, defaultValue: String): String {
		return preferences.getString(key, defaultValue) ?: defaultValue
	}

	fun updateBackgroundColor() {
		val backgroundColorValues = resources.getStringArray(R.array.background_color_values)
		val value = getString(
				resources.getString(R.string.background_color),
				resources.getString(R.string.background_color_default_value)
		)

		when (value) {
			backgroundColorValues[0] -> putString("background_color", "white")
			backgroundColorValues[1] -> putString("background_color", "pink")
			backgroundColorValues[2] -> putString("background_color", "blue")
		}
	}

	fun registerListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.registerOnSharedPreferenceChangeListener(activity)
	}

	fun unregisterListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.unregisterOnSharedPreferenceChangeListener(activity)
	}
}
