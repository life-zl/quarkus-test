package com.example

import com.example.rest.Extension
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Test
import java.lang.RuntimeException


@QuarkusTest
class ExampleResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
                .`when`().get("/hello")
                .then()
                .statusCode(200)
                .body(`is`("Hello from RESTEasy Reactive"))
    }

    @Test
    fun testExtensionsIdEndpoint() {
        given()
                .`when`()["/test/id/io.quarkus:quarkus-rest-client"]
                .then()
                .statusCode(200)
                .body("$.size()", `is`(1),
                        "[0].id", `is`("io.quarkus:quarkus-rest-client"),
                        "[0].name", `is`("REST Client Classic"),
                        "[0].keywords.size()", greaterThan(1),
                        "[0].keywords", hasItem("rest-client"))
    }

    @Test
    fun test() {
        val mockWebServer = MockWebServer()
        mockWebServer.dispatcher = dispatcher()
        mockWebServer.start()

        given()
                .`when`()["/test/id/io.quarkus:quarkus-rest-client"]
                .then()
                .statusCode(200)
                .body("$.size()", `is`(1),
                        "[0].id", `is`("io.quarkus:quarkus-rest-client"),
                        "[0].name", `is`("REST Client Classic"),
                        "[0].keywords.size()", greaterThan(1),
                        "[0].keywords", hasItem("rest-client"))


        mockWebServer.shutdown()


    }

    inner class dispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            when (request.path) {
                    "/api/extensions" ->{
                        return MockResponse().setBody("[{\"id\":\"io.quarkus:quarkus-rest-client\",\"name\":\"REST Client Classic\",\"shortName\":\"\",\"keywords\":[\"call\",\"rest\",\"classic\",\"quarkus-rest-client\",\"web\",\"web-client\",\"rest-client\",\"client\",\"microprofile-rest-client\",\"services\"]}]")
                    }
                else -> RuntimeException("error")
            }
            return MockResponse()
        }

    }

}