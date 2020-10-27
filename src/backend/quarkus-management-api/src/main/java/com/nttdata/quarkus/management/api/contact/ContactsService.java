package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.queryUtils.CursorResultSet;

import java.math.BigInteger;


public interface ContactsService {

    CursorResultSet<Contacts> getContacts(String fromId, int value);

    Contacts getContact(BigInteger contactId);

    Contacts updateContact(Contacts contact);

    Contacts addContact(Contacts contact);

    void deleteContact(BigInteger contactId);

}
