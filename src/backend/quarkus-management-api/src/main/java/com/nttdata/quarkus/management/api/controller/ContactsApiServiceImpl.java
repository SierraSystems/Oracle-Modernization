package com.nttdata.quarkus.management.api.controller;

import com.nttdata.quarkus.management.api.contact.ContactMapper;
import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.openapi.ContactsApi;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import com.nttdata.quarkus.management.api.service.ContactsService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.logging.Logger;
import java.util.stream.Collectors;




@ApplicationScoped
public class ContactsApiServiceImpl implements ContactsApi {

    private static final Logger logger = Logger.getLogger(String.valueOf(ContactsApiServiceImpl.class));

    private final ContactsService contactsService;
    private final ContactMapper contactMapper;

    public ContactsApiServiceImpl(ContactsService contactsService, ContactMapper contactMapper) {
        this.contactsService = contactsService;
        this.contactMapper = contactMapper;
    }


    @Override
    @Transactional
    public Response addContact(@Valid Contact contact, SecurityContext securityContext) {

        logger.info("Add contact");

        Contacts contactToAdd = contactMapper.toContacts(contact);

        return Response.ok(contactMapper.toContact(contactsService.addContact(contactToAdd))).status(201).build();

    }

    @Override
    @Transactional
    public Response deleteContact(BigDecimal contactId, SecurityContext securityContext) {

        logger.info("Delete contact");
        contactsService.deleteContact(contactId.toBigInteger());
        return Response.status(204).build();

    }

    @Override
    @Transactional
    public Response getContact(BigDecimal contactId, SecurityContext securityContext) {

        logger.info("Get contact");
        return Response.ok(contactMapper.toContact(contactsService.getContact(contactId.toBigInteger()))).build();

    }

    @Override
    @Transactional
    public Response getContacts(SecurityContext securityContext) {

        logger.info("Add contacts");
        return Response.ok(
                contactsService
                        .getContacts()
                        .stream()
                        .map(contacts -> contactMapper.toContact(contacts))
                        .collect(Collectors.toList())).build();

    }

    @Override
    @Transactional
    public Response updateContact(@Valid Contact contact, SecurityContext securityContext) {

        logger.info("Update contact");
        Contacts contactToUpdate = contactMapper.toContacts(contact);

        return Response.ok(contactMapper.toContact(contactsService.updateContact(contactToUpdate))).status(200).build();

    }


}
