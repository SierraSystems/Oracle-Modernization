package com.nttdata.quarkus.management.api;

import javax.transaction.Transactional;
import javax.ws.rs.*;

import io.quarkus.panache.common.Sort;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Path("/management")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts")
    @Transactional
    public List<Contacts> getContacts() {
        return Contacts.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts/{contactId}")
    @Transactional
    public Contacts getContacts(@PathParam BigInteger contactId) {
        return Contacts.findById(contactId);
    }

    @POST
    @Path("/contacts/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Contacts contacts) {
        if (contacts.getContactId() != null) {
            throw new WebApplicationException("Id not required.", 422);
        }
        contacts.setContactId(getNextId());
        contacts.persist();
        return Response.ok(contacts).status(201).build();
    }

    @PUT
    @Path("/contacts/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Contacts update(Contacts contacts) {
        Contacts entity = Contacts.findById(contacts.getContactId());
        //contacts.persist();
        entity = contacts;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        Contacts entity = Contacts.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Contacts with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    private BigInteger getNextId() {
       Contacts maxIdContact = (Contacts)Contacts.findAll(Sort.by("CONTACT_ID").descending()).list().get(0);
        //If there are no contacts present it is assumed there are none in the db and one will be the id
        return  maxIdContact.getContactId().add(BigInteger.ONE);
    }

}
