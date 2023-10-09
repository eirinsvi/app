package no.ntnu.idatt2506.oving6.sockets.server

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.*
import java.net.Socket

class ClientHandler(private val socket: Socket, private val connectedClients: ArrayList<Socket>) {

    fun start(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                socket.use {
                    readFromClient(socket) { message: String -> sendToAllClients(message) }
                }
            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    companion object {
        fun readFromClient(socket: Socket, callback: (message: String) -> Unit){
            CoroutineScope(IO).let {
                while (true){
                    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                    val message = reader.readLine()
                    if(message != null){
                        callback(message)
                    }
                }
            }
        }
    }

    private fun sendToAllClients(message: String){
        connectedClients.forEach {
            if(it != socket){
                val writer = PrintWriter(it.getOutputStream(), true)
                writer.println(message)
            }
        }
    }
}