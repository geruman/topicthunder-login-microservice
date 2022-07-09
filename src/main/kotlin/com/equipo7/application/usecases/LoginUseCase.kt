package com.equipo7.application.usecases

import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.repositories.UserRepositoryInterface
import com.equipo7.extensions.toOutput

class LoginUseCase (private val userRepository:UserRepositoryInterface){
    fun loginUser(userName: String, password: String): UserOutput? {
        return userRepository.findUser(userName, password)?.toOutput()
    }
}