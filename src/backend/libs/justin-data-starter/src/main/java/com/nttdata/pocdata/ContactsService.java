package com.nttdata.pocdata;

import com.nttdata.data.Contacts;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ContactsService {
    List<Contacts> getContacts();

    Optional<Contacts> getContact(BigDecimal contactId);

    Contacts saveContact(Contacts contact);

    void deleteContact(BigDecimal contactId);
}
