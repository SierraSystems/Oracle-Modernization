package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.common.QueryHelpers;
import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import com.nttdata.quarkus.management.api.openapi.model.ContactList;
import com.nttdata.quarkus.management.api.openapi.model.ListMetadata;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.List;

@ApplicationScoped
public class ContactsServiceImpl implements ContactsService {


    private final ContactsRepository contactsRepository;

    @Inject
    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


    @Override
    public ContactList getContacts(String fromCursor, int limit) {

        ContactList result = new ContactList();

        BigInteger fromContactId = QueryHelpers.getId(fromCursor, "user");

        int queryLimit = limit + 1;

        List<ContactDto> contacts = contactsRepository.getContactFromCursor(fromContactId, queryLimit);

        if(contacts.size() == queryLimit) {
            ListMetadata metadata = new ListMetadata();
            String nextCursor = MessageFormat.format("user:{0}", contacts.get(limit).getContactId().toString());
            metadata.setNextCursor(Base64.getEncoder().encodeToString(nextCursor.getBytes()));
            result.setMetadata(metadata);
            contacts.remove(limit);
        }

        result.setItems((List<Contact>)(List<?>)contacts);

        return result;

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
