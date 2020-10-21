package com.nttdata.quarkus.management.api.service;

import com.nttdata.quarkus.management.api.model.Contacts;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public interface ContactsService {
    List<Contacts> getContacts();

    Contacts getContact(BigInteger contactId);

    Contacts updateContact(Contacts contact);

    Contacts addContact(Contacts contact);

    void deleteContact(BigInteger contactId);
}
