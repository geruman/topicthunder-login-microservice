package com.equipo7

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.equipo7.routing.*
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
// System.getenv("PORT").toInt()
fun main() {
    embeddedServer(Netty, port = 9191, host = "0.0.0.0") {
        configureJsonEndpoints()
    }.start(wait = true)
}
