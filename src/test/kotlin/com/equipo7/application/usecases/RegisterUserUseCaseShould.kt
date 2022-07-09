package com.equipo7.application.usecases

import com.equipo7.MockitoHelper
import com.equipo7.application.services.interfaces.CoreServiceInterface
import com.equipo7.application.usecases.output.UserOutput
import com.equipo7.core.enitites.User
import com.equipo7.core.repositories.UserRepositoryInterface
import io.ktor.http.*
import org.bson.types.ObjectId
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.BeforeTest

class RegisterUserUseCaseShould {
    lateinit var registerUseCase:RegisterUseCase
    lateinit var repository: UserRepositoryInterface
    lateinit var coreService: CoreServiceInterface
    var username = "German"
    var password ="leondidas"
    @BeforeTest
    fun initialize(){
        repository = Mockito.mock(UserRepositoryInterface::class.java)
        val newUser = User(ObjectId(), username, password)
        Mockito.`when`(repository.createUser(username, password)).thenReturn(newUser)
        coreService = Mockito.mock(CoreServiceInterface::class.java)
        registerUseCase = RegisterUseCase(repository, coreService)
    }
    @Test
    fun createAnewUser() {
        Mockito.`when`(coreService.createUser(MockitoHelper.anyObject())).thenReturn(HttpStatusCode.OK)
        val newUser = registerUseCase.register(username, password)
        Assert.assertNotNull(newUser)
    }

    @Test
    fun createAnewUserInService() {
        Mockito.`when`(coreService.createUser(MockitoHelper.anyObject())).thenReturn(HttpStatusCode.OK)
        val newUser = registerUseCase.register(username, password)
        if(newUser!=null)
            Mockito.verify(coreService).createUser(newUser)
        else
            Assert.fail("User was null")
    }
    @Test
    fun throwAnExceptionWhenNot200inService(){
        Mockito.`when`(coreService.createUser(MockitoHelper.anyObject())).thenReturn(HttpStatusCode.NotFound)
        Assert.assertThrows(Exception::class.java) { registerUseCase.register(username, password) }
    }
    //not call core service when inserting user in repository fails
    @Test
    fun deleteCreatedUserWhenNot200inService(){
        Mockito.`when`(coreService.createUser(MockitoHelper.anyObject())).thenReturn(HttpStatusCode.NotFound)
        try{
            registerUseCase.register(username, password)
        }
        catch (e: Exception){
            Mockito.verify(repository).deleteUser(MockitoHelper.anyObject())
        }
    }
}