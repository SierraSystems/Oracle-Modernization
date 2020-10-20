package com.nttdata.quarkus.management.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.agroal.api.AgroalDataSource;

import java.util.List;

@Path("/hello")
public class GreetingResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello()
    {
        return "mello yellow";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts")
    public List<Contacts> getContacts() {
        return Contacts.listAll();
    }
}