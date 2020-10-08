package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.Contacts;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Comparator;
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
        contact.setContactId(getNextId());
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

    private BigInteger getNextId() {
        Optional<Contacts> maxIdContact = StreamSupport.stream(contactsDao
                        .findAll()
                        .spliterator()
                        , false)
                        .max(Comparator.comparing(Contacts::getContactId));
        //If there are no contacts present it is assumed there are none in the db and one will be the id
        return maxIdContact.isPresent() ?  maxIdContact.get().getContactId().add(BigInteger.ONE) : BigInteger.ONE;
    }


}
