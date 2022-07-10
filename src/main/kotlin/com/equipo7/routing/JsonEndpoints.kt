package com.equipo7.routing

import com.equipo7.application.usecases.LoginUseCase
import com.equipo7.application.usecases.RegisterUseCase
import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User
import com.equipo7.infrastructure.CoreServiceImplementation
import com.equipo7.infrastructure.MongoUserRepository
import com.equipo7.infrastructure.MongodbConnectionHandler
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*

fun createMongoConnection(): MongodbConnectionHandler {
    return MongodbConnectionHandler(
        "mongodb://localhost:27017/",
        "topicthunder_login"
    )
}

fun Application.configureJsonEndpoints() {


    install(ContentNegotiation) {
        json()
    }

    routing {
        post("/login") {
            val parameters = call.receiveParameters()
            val userName = parameters["user"]
            val password = parameters["password"]
            if (userName == null || password == null)
                call.respond(HttpStatusCode.BadRequest, "user and password expected.")
            else {

                val userRepository = MongoUserRepository(
                    createMongoConnection()
                )
                val useCase = LoginUseCase(userRepository)
                val userFound = useCase.loginUser(userName, password)
                if (userFound != null)
                    call.respond(userFound)
                else
                    call.respond(HttpStatusCode.NotFound, "Entity not found")
            }
        }

        post("/register") {
            val parameters = call.receiveParameters()
            val userName = parameters["user"]
            val password = parameters["password"]
            if (userName == null || password == null)
                call.respond(HttpStatusCode.BadRequest, "user and password required")
            else {
                val userRepository = MongoUserRepository(
                    createMongoConnection()
                )
                val useCase = RegisterUseCase(userRepository, CoreServiceImplementation())
                val newUser = useCase.register(userName, password)
                if (newUser != null)
                    call.respond(newUser)
                else
                    call.respond(HttpStatusCode.ExpectationFailed, "Could not register user. Try with different name")
            }
        }
    }

}
