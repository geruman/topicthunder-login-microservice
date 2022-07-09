package com.equipo7.application.services.interfaces

import com.equipo7.application.usecases.output.UserOutput
import io.ktor.http.*

interface CoreServiceInterface {
    fun createUser(user: UserOutput) : HttpStatusCode
}
