package com.example.rest

import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MultivaluedMap

@ApplicationScoped
class RequestUUIDHeaderFactory : ClientHeadersFactory {

    @ConfigProperty(name = "x.token.key", defaultValue = "default")
    private lateinit var key: String

    override fun update(incomingHeaders: MultivaluedMap<String, String>?, clientOutgoingHeaders: MultivaluedMap<String, String>?): MultivaluedMap<String, String> {
        clientOutgoingHeaders!!.add("token.key", key)
        return clientOutgoingHeaders
    }
}