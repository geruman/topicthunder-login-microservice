package com.equipo7.routing

import com.equipo7.application.usecases.RegisterUseCase
import com.equipo7.createMongoConnection
import com.equipo7.infrastructure.CoreServiceImplementation
import com.equipo7.infrastructure.MongoUserRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.registerEndpoint() {



    routing {

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
