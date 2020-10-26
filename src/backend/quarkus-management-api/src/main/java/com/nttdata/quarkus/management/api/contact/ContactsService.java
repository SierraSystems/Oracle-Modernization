package com.nttdata.quarkus.management.api.contact;

import com.nttdata.pocdata.hibernate.Contacts;

import java.math.BigInteger;
import java.util.List;


public interface ContactsService {
    List<Contacts> getContacts();

    Contacts getContact(BigInteger contactId);

    Contacts updateContact(Contacts contact);

    Contacts addContact(Contacts contact);

    void deleteContact(BigInteger contactId);
}
