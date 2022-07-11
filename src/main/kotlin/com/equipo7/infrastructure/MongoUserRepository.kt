package com.equipo7.infrastructure

import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface
import org.bson.types.ObjectId
import org.litote.kmongo.and
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

class MongoUserRepository(private val connectionHandler:MongodbConnectionHandler) : UserRepositoryInterface {
    override fun findUser(userName: String, password: String): User? {
        var userFound:User? = null
        try{
            connectionHandler.connect()
            val col = connectionHandler.getCollection<User>("User")
             userFound = col.findOne(and(User::name eq userName, User::password eq password))
        }catch(exception:Exception ){
            exception.printStackTrace()
        }finally {
            connectionHandler.closeConnection()
        }
        return userFound
    }

    override fun createUser(userName: String, password: String): User? {
        var userCreated:User? = null
        try {
            connectionHandler.connect()
            val col = connectionHandler.getCollection<User>("User")
            val userFound = col.findOne(User::name eq userName)
            if (userFound == null) {
                val userToInsert = User(ObjectId(), userName, password)
                if(col.insertOne(userToInsert).wasAcknowledged()){
                    userCreated = userToInsert
                }
            }
        }catch(exception:Exception ){
            exception.printStackTrace()
        }finally {
            connectionHandler.closeConnection()
        }
        return userCreated
    }

    override fun deleteUser(user: User) {
        try {
            connectionHandler.connect()
            val col = connectionHandler.getCollection<User>("User")
            col.deleteOneById(user._id)
        }catch(exception:Exception ){
            exception.printStackTrace()
        }finally {
            connectionHandler.closeConnection()
        }
    }
}
