package com.equipo7.core.repositories

import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User

interface UserRepositoryInterface {
    fun findUser(userName:String, password:String): User?
    fun createUser(userName:String, password:String): User?
}