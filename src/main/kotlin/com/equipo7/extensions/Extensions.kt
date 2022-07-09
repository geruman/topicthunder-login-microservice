package com.equipo7.extensions

import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User

fun User.toOutput():UserOutput{
    return UserOutput(_id.toString(), name)
}