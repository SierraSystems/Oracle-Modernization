package com.nttdata.quarkus.management.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.nttdata.pocdata.InventoriesService;
import com.nttdata.pocdata.hibernate.Contacts;
import org.springframework.http.ResponseEntity;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "mello yellow";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts")
    public ResponseEntity<Contacts> getContacts() {
        return ResponseEntity.ok(new Contacts());
    }
}