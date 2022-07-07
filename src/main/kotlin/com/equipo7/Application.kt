package com.equipo7

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.equipo7.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt(), host = "topic-thunder-backend.herokuapp.com") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
