package com.nttdata.quarkus.management.api.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;

import com.nttdata.quarkus.management.api.model.Contacts;
import com.nttdata.quarkus.management.api.service.ContactsService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;

@Path("/management")
public class ContactsResource {

    @Inject
    ContactsService contactsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts")
    @Transactional
    public List<Contacts> getContacts() {
        return contactsService.getContacts();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts/{contactId}")
    @Transactional
    public Contacts getContacts(@PathParam BigInteger contactId) {
        return contactsService.getContact(contactId);
    }

    @POST
    @Path("/contacts/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Contacts contacts) {

        if (contacts.getContactId() != null) {
            throw new WebApplicationException("Id not required.", 422);
        }

        return Response.ok(contactsService.addContact(contacts)).status(201).build();
    }

    @PUT
    @Path("/contacts/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Contacts contacts) {
        return Response.ok(contactsService.updateContact(contacts)).status(200).build();
    }

    @DELETE
    @Path("/contacts/{contactId}/delete")
    @Transactional
    public Response delete(@PathParam BigInteger contactId) {
        contactsService.deleteContact(contactId);
        return Response.status(204).build();
    }

}
