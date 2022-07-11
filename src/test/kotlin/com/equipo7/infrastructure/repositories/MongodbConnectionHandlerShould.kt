package com.equipo7.infrastructure.repositories

import com.equipo7.core.enitites.User
import com.equipo7.infrastructure.MongodbConnectionHandler
import org.bson.types.ObjectId
import org.junit.Assert
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import kotlin.test.BeforeTest
import kotlin.test.Test

class MongodbConnectionHandlerShould {
    private lateinit var mongoData:MongodbConnectionHandler
    @BeforeTest
    fun initialize(){
        //"mongodb://localhost:27017/"
        mongoData = MongodbConnectionHandler("mongodb+srv://german:FstGMkT0jVMuqpmr@cluster0.3xfnu.mongodb.net/?retryWrites=true&w=majority","topicthunder_login_tests")

    }
    @Test
    fun findAnUserCollectionAndInsertAuserAndRetrieveIt(){
        mongoData.connect()
        val col = mongoData.getCollection<User>("User")
        col.insertOne(User(ObjectId(),"German","Pass"))
        val user = col.findOne(and(User::name eq "German", User::password eq "Pass"))
        Assert.assertEquals("German",user?.name)
        mongoData.closeConnection()
    }
}