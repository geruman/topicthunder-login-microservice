package com.equipo7.routing

import com.equipo7.application.usecases.LoginUseCase
import com.equipo7.createMongoConnection
import com.equipo7.infrastructure.MongoUserRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Application.loginEndpoint() {




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


    }

}
