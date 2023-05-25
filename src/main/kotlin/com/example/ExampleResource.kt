package com.example

import com.example.request.UserRequest
import com.example.response.UserResponse
import com.example.rest.Extension
import com.example.rest.ExtensionsService
import com.example.service.GreetingService
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/test")
class ExampleResource {

    @Inject
    lateinit var greetingService: GreetingService

    @Inject
    @RestClient
    internal lateinit var extensionsService: ExtensionsService

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from RESTEasy Reactive"

    @POST
    @Path("hello2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun hello2(userRequest: UserRequest): UserResponse {
        println("request is ${userRequest.username}")
        println(greetingService.greeting("zhangsan"))
        return UserResponse("000000", "ok")
    }

    @GET
    @Path("/id/{id}")
    fun id(id: String): Set<Extension> {
        println("id is $id")
        return extensionsService.getById(id)
    }
}