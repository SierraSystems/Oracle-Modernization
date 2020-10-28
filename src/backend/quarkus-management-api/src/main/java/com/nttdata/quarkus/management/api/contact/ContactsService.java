package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.openapi.model.ContactList;

import java.math.BigInteger;


public interface ContactsService {

    ContactList getContacts(String fromCursor, int limit);

    Contacts getContact(BigInteger contactId);

    Contacts updateContact(Contacts contact);

    Contacts addContact(Contacts contact);

    void deleteContact(BigInteger contactId);
}
