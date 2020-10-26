package com.nttdata.quarkus.management.api.service;

import com.nttdata.quarkus.management.api.dao.ContactsRepository;
import com.nttdata.quarkus.management.api.model.database.Contacts;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public class ContactsServiceImpl implements ContactsService {

    @Inject
    ContactsRepository contactsRepository;

    @Override
    public List<Contacts> getContacts() {
        return contactsRepository.loadWithCustomer();
    }

    @Override
    public Contacts getContact(BigInteger contactId) {
        return contactsRepository.findById(contactId);
    }

    @Override
    public Contacts updateContact(Contacts contact) {
        //MAGIC TIME
        Contacts entity = contactsRepository.findById(contact.getContactId());

        entity.setLastName(contact.getLastName());
        entity.setFirstName(contact.getFirstName());
        entity.setPhone(contact.getPhone());
        entity.setEmail(contact.getEmail());

        return entity;
    }

    @Override
    public Contacts addContact(Contacts contact) {
        contact.setContactId(getNextId());
        contactsRepository.persist(contact);
        return contact;
    }

    @Override
    public void deleteContact(BigInteger contactId) {
        Contacts entity = contactsRepository.findById(contactId);
        contactsRepository.delete(entity);
    }

    private BigInteger getNextId() {
        Contacts maxIdContact = contactsRepository.findAll(Sort.by("CONTACT_ID").descending()).list().get(0);

        return  maxIdContact.getContactId().add(BigInteger.ONE);
    }
}
