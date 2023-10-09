package no.ntnu.idatt2506.oving6.sockets.server

import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val textView: TextView, private val PORT: Int = 12345) {

	private val allSocketsConnected: ArrayList<Socket> = ArrayList()
	private var messages: String? = null

	private var ui: String? = ""
		set(str) {
			messages += str
			MainScope().launch { textView.text = messages }
			field += str
		}

	fun start() {
		CoroutineScope(Dispatchers.IO).launch {
			try {
				ui = "Starter Tjener ..."
				ServerSocket(PORT).use { serverSocket: ServerSocket ->
					while (true){
						val socketConnection = serverSocket.accept()
						ui = "En Klient koblet seg til:\n$socketConnection"
						allSocketsConnected.add(socketConnection)
						ClientHandler(socketConnection, allSocketsConnected).start()
					}
				}
			} catch (e: IOException) {
				e.printStackTrace()
				ui = e.message
			}
		}
	}
}
