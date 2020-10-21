package com.nttdata.quarkus.management.api.service;

import com.nttdata.quarkus.management.api.dao.ContactsDao;
import com.nttdata.quarkus.management.api.model.database.Contacts;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public class ContactsServiceImpl implements ContactsService {

    @Inject
    ContactsDao contactsDao;

    @Override
    public List<Contacts> getContacts() {
        return contactsDao.findAll(Sort.by("CONTACT_ID").ascending()).list();
    }

    @Override
    public Contacts getContact(BigInteger contactId) {
        return contactsDao.findById(contactId);
    }

    @Override
    public Contacts updateContact(Contacts contact) {
        //MAGIC TIME
        Contacts entity = contactsDao.findById(contact.getContactId());

        entity.setLastName(contact.getLastName());
        entity.setFirstName(contact.getFirstName());
        entity.setPhone(contact.getPhone());
        entity.setEmail(contact.getEmail());

        return entity;
    }

    @Override
    public Contacts addContact(Contacts contact) {
        contact.setContactId(getNextId());
        contactsDao.persist(contact);
        return contact;
    }

    @Override
    public void deleteContact(BigInteger contactId) {
        Contacts entity = contactsDao.findById(contactId);
        if (entity == null) {
            throw new WebApplicationException("Contact does not exist.", 404);
        }
        contactsDao.delete(entity);
    }

    private BigInteger getNextId() {
        Contacts maxIdContact = contactsDao.findAll(Sort.by("CONTACT_ID").descending()).list().get(0);

        return  maxIdContact.getContactId().add(BigInteger.ONE);
    }
}
