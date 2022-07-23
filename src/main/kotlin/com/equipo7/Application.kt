package com.equipo7

import com.equipo7.infrastructure.MongodbConnectionHandler
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.equipo7.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

//
fun createMongoConnection(): MongodbConnectionHandler {
    return MongodbConnectionHandler(
        "mongodb+srv://german:FstGMkT0jVMuqpmr@cluster0.3xfnu.mongodb.net/?retryWrites=true&w=majority",
        "topicthunder_login"
    )
    //"mongodb://localhost:27017/",
}
fun main() {
    //val port = System.getenv("PORT").toInt()
    val port = 8181
    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }
        loginEndpoint()
        registerEndpoint()
    }.start(wait = true)
}
