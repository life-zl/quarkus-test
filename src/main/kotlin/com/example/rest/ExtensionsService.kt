package com.example.rest

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

@Path("/extensions")
@RegisterRestClient(configKey="extensions-api")
@RegisterClientHeaders(RequestUUIDHeaderFactory::class)
interface ExtensionsService {
    @GET
    fun getById(@QueryParam("id") id: String): Set<Extension>
}