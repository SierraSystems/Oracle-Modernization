package com.nttdata.quarkus.management.api.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;

import com.nttdata.quarkus.management.api.model.api.Contact;
import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.service.ContactsService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Path("/management")
@ApplicationScoped
public class ContactsResource {

    @Inject
    ContactsService contactsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts")
    @Transactional
    public List<Contact> getContacts() {

        return contactsService.getContacts().stream()
                .map(this::mapContact)
                .collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/contacts/{contactId}")
    @Transactional
    public Contact getContact(@PathParam BigInteger contactId) {
        return mapContact(contactsService.getContact(contactId));
    }

    @POST
    @Path("/contacts/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Contact contact) {

        if (contact.getContactId() != null) {
            throw new WebApplicationException("Id not required.", 422);
        }

        return Response.ok(contactsService.addContact(mapContacts(contact))).status(201).build();
    }

    @PUT
    @Path("/contacts/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Contact contact) {
        return Response.ok(contactsService.updateContact(mapContacts(contact))).status(200).build();
    }

    @DELETE
    @Path("/contacts/{contactId}/delete")
    @Transactional
    public Response delete(@PathParam BigInteger contactId) {
        contactsService.deleteContact(contactId);
        return Response.status(204).build();
    }

    private Contacts mapContacts(Contact contact) {

        Contacts contacts = new Contacts();
        contacts.setLastName(contact.getLastName());
        contacts.setFirstName(contact.getFirstName());
        contacts.setContactId((contact.getContactId() != null) ? contact.getContactId().toBigInteger() : null);
        contacts.setEmail(contact.getEmail());
        contacts.setPhone(contact.getPhoneNumber());
        return contacts;

    }

    private Contact mapContact(Contacts contacts) {

        Contact contact = new Contact();
        contact.setLastName(contacts.getLastName());
        contact.setFirstName(contacts.getFirstName());
        contact.setContactId(BigDecimal.valueOf(contacts.getContactId().longValue()));
        contact.setEmail(contacts.getEmail());
        contact.setPhoneNumber(contacts.getPhone());
        if (contacts.getCustomers() != null && contacts.getCustomers().getCustomerId() != null)
            contact.setCustomerId(BigDecimal.valueOf(contacts.getCustomers().getCustomerId().longValue()));
        return contact;

    }

}
