package com.nttdata.quarkus.management.api.controller;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.openapi.ManagementApi;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import com.nttdata.quarkus.management.api.service.ContactsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@ApplicationScoped
public class ManagementApiImpl implements ManagementApi {

    @Inject
    ContactsService contactsService;

    @Override
    public Response addContact(@Valid Contact contact, SecurityContext securityContext) {
        return Response.ok(contactsService.addContact(mapContacts(contact))).status(201).build();
    }

    @Override
    public Response deleteContact(BigDecimal contactId, SecurityContext securityContext) {
        contactsService.deleteContact(contactId.toBigInteger());
        return Response.status(204).build();
    }

    @Override
    public Response getContact(BigDecimal contactId, SecurityContext securityContext) {
        return Response.ok(mapContact(contactsService.getContact(contactId.toBigInteger()))).build();
    }

    @Override
    @Transactional
    public Response getContacts(SecurityContext securityContext) {
        return Response.ok(
                contactsService
                        .getContacts()
                        .stream()
                        .map(this::mapContact)
                        .collect(Collectors.toList())).build();
    }

    @Override
    public Response updateContact(@Valid Contact contact, SecurityContext securityContext) {
        return Response.ok(contactsService.updateContact(mapContacts(contact))).status(200).build();
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

    private Contacts mapContacts(Contact contact) {

        Contacts contacts = new Contacts();

        if(contact.getContactId() != null)
            contacts.setContactId(contact.getContactId().toBigInteger());

        contacts.setLastName(contact.getLastName());
        contacts.setFirstName(contact.getFirstName());
        contacts.setContactId((contact.getContactId() != null) ? contact.getContactId().toBigInteger() : null);
        contacts.setEmail(contact.getEmail());
        contacts.setPhone(contact.getPhoneNumber());

        return contacts;

    }
}
