package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.Contacts;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactsServiceImpl implements ContactsService {

    private final ContactsDao contactsDao;

    public ContactsServiceImpl(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    @Override
    public List<Contacts> getContacts() {
        return StreamSupport.stream(contactsDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Contacts> getContact(BigInteger contactId) {
        return contactsDao.findById(contactId);
    }

    @Override
    public Contacts addContact(Contacts contact) {
        contact.setContactId(BigInteger.valueOf(StreamSupport.stream(contactsDao.findAll().spliterator(), false).count()));
        return contactsDao.save(contact);
    }

    @Override
    public Contacts updateContact(Contacts contact) {
        return contactsDao.save(contact);
    }

    @Override
    public void deleteContact(BigInteger contactId) {
        contactsDao.deleteById(contactId);
    }
}
