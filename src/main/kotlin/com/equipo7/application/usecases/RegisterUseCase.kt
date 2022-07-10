package com.equipo7.application.usecases

import com.equipo7.application.services.interfaces.CoreServiceInterface
import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface
import com.equipo7.extensions.toOutput
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

class RegisterUseCase(val repository: UserRepositoryInterface, val coreService: CoreServiceInterface) {
    fun register(username: String, password: String): UserOutput? {
        val newUser: User? = repository.createUser(username, password)
        if (newUser != null) {
            try {
                val result = runBlocking {
                    coreService.createUser(newUser.toOutput())
                }
                if (HttpStatusCode.OK.value != result.value) {
                    throw Exception("Error in creating user in Core, expected result 200 got " + result.value)
                }
            } catch (ex: Exception) {
                repository.deleteUser(newUser)
                throw ex
            }
        }
        return newUser?.toOutput()
    }

}
