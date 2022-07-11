package com.equipo7

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.equipo7.routing.*
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
//
fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "0.0.0.0") {
        configureJsonEndpoints()
    }.start(wait = true)
}
