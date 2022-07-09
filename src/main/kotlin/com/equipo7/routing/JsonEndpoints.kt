package com.equipo7.routing
import com.equipo7.core.enitites.User
import io.ktor.serialization.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bson.types.ObjectId
import org.litote.kmongo.*


fun Application.configureJsonEndpoints() {
    val client = KMongo.createClient("mongodb://localhost:27017/");
    val database = client.getDatabase("topicthunder_login")
    val col = database.getCollection<User>("User")


    install(ContentNegotiation) {
        json()
    }

    routing {
        post("/login") {
            val parameters = call.receiveParameters()
            val userName = parameters["user"]
            val password = parameters["password"]
            val userFound : User? = col.findOne(and(User::name eq userName, User::password eq password))
            if (userFound!=null)
                call.respond(userFound)
            else
                call.respond(HttpStatusCode.NotFound,"Entity not found")
        }
    }
}
