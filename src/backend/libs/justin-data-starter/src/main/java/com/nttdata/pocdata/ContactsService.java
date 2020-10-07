package com.nttdata.pocdata;

import com.nttdata.data.Contacts;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ContactsService {
    List<Contacts> getContacts();

    Optional<Contacts> getContact(BigInteger contactId);

    Contacts updateContact(Contacts contact);

    Contacts addContact(Contacts contact);

    void deleteContact(BigInteger contactId);
}
