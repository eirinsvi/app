package no.ntnu.idatt2506.oving6.sockets.client

import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(
	private val textView: TextView,
	private val SERVER_IP: String = "10.0.2.2",
	private val SERVER_PORT: Int = 12345,
) {
	private var connection: Socket? = null
	private var messages: String? = null


	/**
	 * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
	 * emulatoren med
	 *
	 * ```
	 * ui = "noe"
	 * ```
	 */
	private var ui: String? = ""
		set(str) {
			messages += str
			MainScope().launch { textView.text = messages }
			field = str
		}



	fun start() {
		CoroutineScope(Dispatchers.IO).launch {
			ui = "Kobler til tjener..."
			try {
				connection = Socket(SERVER_IP, SERVER_PORT)

				ui = "Koblet til tjener:\n$connection"
				connection?.let {
					readFromServer(connection!!)
				}

			} catch (e: IOException) {
				e.printStackTrace()
				ui = e.message
			}
		}
	}

	private fun readFromServer(socket: Socket) {
		while(true){
			val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
			val message = reader.readLine()
			ui = "\nMotatt:\n$message"
		}
	}

	fun sendToServer(message: String) {
		CoroutineScope(Dispatchers.IO).launch {
			if(connection != null) {
				val writer = PrintWriter(connection?.getOutputStream(), true)
				writer.println(message)
				ui = "\nSendt: \n$message"
			}
		}
	}
}
