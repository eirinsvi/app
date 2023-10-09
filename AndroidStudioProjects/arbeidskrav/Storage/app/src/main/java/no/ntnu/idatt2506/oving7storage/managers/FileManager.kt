package no.ntnu.idatt2506.oving7storage.managers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import java.io.*

/**
 * Just contains basic code snippets relevant for reading from/to different files
 */
class FileManager(private val activity: AppCompatActivity) {

	private val filename: String = "movies.txt"

	private var dir: File = activity.filesDir
	private var file: File = File(dir, filename)

	fun write(str: String, fileName: String) {
		PrintWriter(fileName).use { writer ->
			writer.println(str)
		}
	}

	/**
	 * Open file: *res/raw/id.txt*
	 *
	 * @param fileId R.raw.filename
	 */
	fun readFileFromResFolder(fileId: Int): String {
		val content = StringBuffer("")
		try {
			val inputStream: InputStream = activity.resources.openRawResource(fileId)
			BufferedReader(InputStreamReader(inputStream)).use { reader ->
				var line = reader.readLine()
				while (line != null) {
					content.append(line)
					content.append("\n")
					line = reader.readLine()
				}

			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return content.toString()
	}
}
