package com.equipo7.infrastructure.repositories

import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface
import com.equipo7.infrastructure.MongoUserRepository
import com.equipo7.infrastructure.MongodbConnectionHandler
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.junit.Assert
import kotlin.test.BeforeTest
import kotlin.test.Test

//ESTA ES UN TEST INTEGRAL CONTRA MONGO
class UserRepositoryShould {

    private lateinit var mongoConnectionHandler:MongodbConnectionHandler
    private lateinit var userRepository:UserRepositoryInterface
    @BeforeTest
    fun initialize(){
        mongoConnectionHandler = MongodbConnectionHandler("mongodb://localhost:27017/", "topicthunder_login_tests")
        userRepository = MongoUserRepository(mongoConnectionHandler)
        userRepository.createUser("German","Password")
    }
    @Test
    fun returnUser(){
        val user = userRepository.findUser("German","Password")
        Assert.assertNotNull(user)
    }
}