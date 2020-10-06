package com.nttdata.pocdata;

import com.nttdata.data.Contacts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContactsServiceImpl implements ContactsService {

    private final ContactsDao contactsDao;

    public ContactsServiceImpl(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    public List<Contacts> getContacts() {
        return StreamSupport.stream(contactsDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
