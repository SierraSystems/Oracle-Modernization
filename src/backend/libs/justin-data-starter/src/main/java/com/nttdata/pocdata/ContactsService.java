package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.Contacts;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ContactsService {
    List<Contacts> getContacts();

    Optional<Contacts> getContact(BigInteger contactId);

    Contacts saveContact(Contacts contact);

    void deleteContact(BigInteger contactId);
}
