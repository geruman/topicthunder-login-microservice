package com.equipo7.application.usecases.output

import kotlinx.serialization.Serializable

@Serializable
class UserOutput (val id:String,val userName:String){
    override fun equals(other: Any?): Boolean {
        return if(other is UserOutput){
            hashCode()==other.hashCode()
        }else{
            false
        }
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userName.hashCode()
        return result
    }
}
