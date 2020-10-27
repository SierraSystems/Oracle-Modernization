package com.nttdata.quarkus.management.api.contact;

import com.nttdata.pocdata.hibernate.Contacts;
import com.nttdata.quarkus.management.api.openapi.ContactsApi;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import com.nttdata.quarkus.management.api.openapi.model.ContactResponse;
import com.nttdata.quarkus.management.api.openapi.model.ContactResponseResponseMetadata;
import com.nttdata.quarkus.management.api.queryUtils.CursorResultSet;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Base64;
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
        return Response.ok().build();

    }

    @Override
    @Transactional
    public Response getContact(BigDecimal contactId, SecurityContext securityContext) {

        logger.info("Get contact");
        return Response.ok(contactMapper.toContact(contactsService.getContact(contactId.toBigInteger()))).build();

    }

    @Override
    @Transactional
    public Response getContacts(String cursor, Integer limit, SecurityContext securityContext) {

        if(limit == null || limit >= 100) limit = 100;

        String fromId = "";

        if(!StringUtils.isBlank(cursor)) {
            byte[] decodedBytes = Base64.getDecoder().decode(cursor);
            String decodedString = new String(decodedBytes);
            fromId = decodedString.split(":")[1];
        }


        CursorResultSet<Contacts> resultSet = contactsService.getContacts(fromId, limit);

        ContactResponse response = new ContactResponse();
        response.setItems(resultSet.getItems()
                .stream()
                .map(contacts -> contactMapper.toContact(contacts))
                .collect(Collectors.toList()));

        ContactResponseResponseMetadata metadata = new ContactResponseResponseMetadata();

        if(!StringUtils.isBlank(resultSet.getNextCursor())) {
            metadata.setNextCursor(Base64.getEncoder().encodeToString(MessageFormat.format("user:{0}",resultSet.getNextCursor()).getBytes()));
            response.setResponseMetadata(metadata);
        }


        logger.info("Add contacts");
        return Response.ok(response).build();

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
