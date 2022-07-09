package com.equipo7

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.equipo7.routing.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureJsonEndpoints() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

}