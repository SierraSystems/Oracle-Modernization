package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.openapi.ContactsApi;
import com.nttdata.quarkus.management.api.openapi.model.Contact;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.logging.Logger;




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
        return Response.ok().build();

    }

    @Override
    @Transactional
    public Response getContact(BigDecimal contactId, SecurityContext securityContext) {

        logger.info("Get contact");
        return Response.ok(contactMapper.toContact(contactsService.getContact(contactId.toBigInteger()))).build();

    }

    @Override
    public Response getContacts(@Min(1) @Max(100) Integer limit, String fromcursor, SecurityContext securityContext) {


        logger.info("Add contacts");
        return Response.ok(
                contactsService.getContacts(fromcursor, limit)).build();

    }

    @Override
    @Transactional
    public Response updateContact(@Valid Contact contact, SecurityContext securityContext) {

        logger.info("Update contact");
        return Response.ok(
                contactMapper.toContact(
                        contactsService.updateContact(contactMapper.toContacts(contact))))
                .status(200).build();

    }

}
