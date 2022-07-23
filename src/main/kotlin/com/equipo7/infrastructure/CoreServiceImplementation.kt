package com.equipo7.infrastructure

import com.equipo7.application.services.interfaces.CoreServiceInterface
import com.equipo7.application.usecases.output.UserOutput
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.bson.types.ObjectId

const val coreServiceURL = "https://topicthunderapi.herokuapp.com"
//const val coreServiceURL = "http://localhost:8080"
class CoreServiceImplementation: CoreServiceInterface {
    override suspend fun createUser(user: UserOutput): HttpStatusCode {
        val client = HttpClient(CIO){
            install(HttpTimeout){
                requestTimeoutMillis = 30000
            }
        }

        val response: HttpResponse = client.submitForm<HttpResponse>(
            url = "$coreServiceURL/user/register",
            formParameters = Parameters.build {
                append("id", user.id)
                append("username", user.userName)
            })
        return response.status
    }
}