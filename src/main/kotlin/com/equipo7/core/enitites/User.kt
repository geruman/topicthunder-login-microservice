package com.equipo7.core.enitites

import org.bson.types.ObjectId

//@Serializable
data class User(val _id: ObjectId, val name: String, val password: String)

//@Serializable(with = ObjectIdAsString::class) val _id: ObjectId
/*
object ObjectIdAsString: KSerializer<ObjectId> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("ObjectId", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): ObjectId {
        return ObjectId(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: ObjectId) {
        encoder.encodeString(value.toString())
    }
}*/