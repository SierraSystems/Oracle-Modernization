package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.Contacts;

import java.util.List;

public interface ContactsService {
    List<Contacts> getContacts();
}
