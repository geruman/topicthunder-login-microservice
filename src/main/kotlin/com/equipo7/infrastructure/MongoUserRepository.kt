package com.equipo7.infrastructure

import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface

class MongoUserRepository(private val connectionHandler:MongodbConnectionHandler) : UserRepositoryInterface {
    override fun findUser(userName: String, password: String): User? {
        try{
            connectionHandler.connect()
            val col = connectionHandler.getCollection<User>("User")
            col.find()
        }catch(exception:Exception ){
            Log
        }finally {
            connectionHandler.closeConnection()
        }
    }

    override fun createUser(userName: String, password: String): User {
        TODO("Not yet implemented")
    }


}
