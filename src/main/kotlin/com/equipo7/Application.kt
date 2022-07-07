package com.equipo7

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.equipo7.plugins.*

fun main() {
    embeddedServer(Netty, port = 80, host = "topic-thunder-backend.herokuapp.com") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
