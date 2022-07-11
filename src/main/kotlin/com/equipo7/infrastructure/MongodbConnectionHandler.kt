package com.equipo7.infrastructure

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

class MongodbConnectionHandler(private val connectionString:String, private val databaseName:String) {
    private lateinit var client:MongoClient
    lateinit var database: MongoDatabase
    fun connect(){
        client =  KMongo.createClient(connectionString);
        database = client.getDatabase(databaseName);
    }
    inline fun <reified T : Any>getCollection(collectionName:String):MongoCollection<T>{
        return database.getCollection(collectionName,T::class.java)
    }
    fun closeConnection(){
        client.close()
    }
}