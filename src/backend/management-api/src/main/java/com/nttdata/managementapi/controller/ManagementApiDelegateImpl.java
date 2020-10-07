package com.nttdata.managementapi.controller;

import com.nttdata.managementapi.api.ManagementApiDelegate;
import com.nttdata.managementapi.api.model.Contact;
import com.nttdata.pocdata.ContactsService;
import com.nttdata.pocdata.hibernate.Contacts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagementApiDelegateImpl implements ManagementApiDelegate {

    private final ContactsService contactService;

    public ManagementApiDelegateImpl(ContactsService contactService) {
        this.contactService = contactService;
    }

    @Override
    public ResponseEntity<Contact> getContact(BigDecimal contactId) {
        Contact contact = new Contact();
        contact.setContactId(BigDecimal.ONE);
        contact.setCustomerId(BigDecimal.ONE);
        contact.setEmail("bobross@paintit.com");
        contact.setFirstName("Bob");
        contact.setLastName("Ross");
        contact.setPhoneNumber("123-456-7890");
        return ResponseEntity.ok(contact);
    }

    @Override
    public ResponseEntity<List<Contact>> getContacts() {


        return ResponseEntity.ok(contactService.getContacts().stream()
                .map(contacts -> mapContact(contacts))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Contact> addContact(Contact contact) {
        return new ResponseEntity(contact, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Contact> updateContact(Contact contact) {
        return ResponseEntity.ok(contact);
    }

    private Contact mapContact(Contacts contacts) {
        Contact contact = new Contact();
        contact.setLastName(contacts.getLastName());
        contact.setFirstName(contacts.getFirstName());
        contact.setContactId(BigDecimal.valueOf(contacts.getContactId().longValue()));
        contact.setEmail(contacts.getEmail());
        contact.setPhoneNumber(contacts.getPhone());
        return contact;
    }
}
