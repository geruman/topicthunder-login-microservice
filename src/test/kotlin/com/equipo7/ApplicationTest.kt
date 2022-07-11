package com.equipo7

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.equipo7.routing.*

@Ignore
class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureJsonEndpoints() }) {
            handleRequest(HttpMethod.Get, "/login").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

}