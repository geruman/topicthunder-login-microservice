package com.equipo7.application.usecases

import com.equipo7.application.services.interfaces.CoreServiceInterface
import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface
import com.equipo7.extensions.toOutput
import io.ktor.http.*

class RegisterUseCase(val repository: UserRepositoryInterface, val coreService: CoreServiceInterface) {
    fun register(username: String, password: String): UserOutput? {
        val newUser: User? = repository.createUser(username, password)
        if(newUser!= null){
            val result = coreService.createUser(newUser.toOutput())
            if(result != HttpStatusCode.OK){
                repository.deleteUser(newUser)
                throw Exception("Error in creating user in Core, expected result 200 got "+result.value)
            }
        }
        return newUser?.toOutput()
    }

}
