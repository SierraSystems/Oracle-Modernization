package com.nttdata.pocdata;

import com.nttdata.data.Contacts;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public Optional<Contacts> getContact(BigDecimal contactId) {
        return contactsDao.findById(contactId);
    }

    @Override
    public Contacts saveContact(Contacts contact) {
        return contactsDao.save(contact);
    }

    @Override
    public void deleteContact(BigDecimal contactId) {

    }
}
