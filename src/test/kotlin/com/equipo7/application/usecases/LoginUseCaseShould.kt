package com.equipo7.application.usecases

import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface
import org.bson.types.ObjectId
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.BeforeTest

class LoginUseCaseShould {
    private lateinit var useCase:LoginUseCase
    private val userName = "German"
    private val password = "password"
    @BeforeTest
    fun initialize(){
        val repository:UserRepositoryInterface = Mockito.mock(UserRepositoryInterface::class.java)
        val user  = User(ObjectId(),userName,password)
        Mockito.`when`(
            repository.findUser(userName,password)
        ).thenReturn(
            user
        )
        useCase = LoginUseCase(repository)
    }
    @Test
    fun returnUser(){
        val user:UserOutput? = useCase.loginUser(userName, password)
        Assert.assertNotNull(user)
    }
    @Test
    fun returnNullUser(){
        val user:UserOutput? = useCase.loginUser("Gerardo", password)
        Assert.assertNull(user)
    }
}