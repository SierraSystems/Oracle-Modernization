package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public class ContactsServiceImpl implements ContactsService {


    private final ContactsRepository contactsRepository;

    @Inject
    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


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

        this.contactsRepository.getEntityManager().merge(contact);
        return contact;

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
