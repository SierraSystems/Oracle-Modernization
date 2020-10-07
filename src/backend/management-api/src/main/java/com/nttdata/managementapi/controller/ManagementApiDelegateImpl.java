package com.nttdata.managementapi.controller;

import com.nttdata.data.Contacts;
import com.nttdata.managementapi.api.ManagementApiDelegate;
import com.nttdata.managementapi.api.model.Contact;
import com.nttdata.pocdata.ContactsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagementApiDelegateImpl implements ManagementApiDelegate {

    private final ContactsService contactService;

    public ManagementApiDelegateImpl(ContactsService contactService) {
        this.contactService = contactService;
    }

    @Override
    public ResponseEntity<Contact> getContact(BigDecimal contactId) {

        Optional<Contacts> contacts = contactService.getContact(contactId.toBigInteger());

        return contacts.map(value -> ResponseEntity.ok(mapContact(value))).orElseGet(() -> new ResponseEntity("Client not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public ResponseEntity<List<Contact>> getContacts() {

        return ResponseEntity.ok(contactService.getContacts().stream()
                .map(this::mapContact)
                .collect(Collectors.toList()));

    }

    @Override
    public ResponseEntity<Contact> addContact(Contact contact) {

        Contacts contacts = contactService.addContact(mapContacts(contact));
        return new ResponseEntity(mapContact(contacts), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Contact> updateContact(Contact contact) {

        Contacts contacts = contactService.updateContact(mapContacts(contact));
        return ResponseEntity.ok(mapContact(contacts));

    }

    @Override
    public ResponseEntity<Void> deleteContact(BigDecimal contactId) {

        contactService.deleteContact(contactId.toBigInteger());
        return ResponseEntity.ok().build();

    }

    private Contacts mapContacts(Contact contact) {

        Contacts contacts = new Contacts();
        contacts.setLastName(contact.getLastName());
        contacts.setFirstName(contact.getFirstName());
        contacts.setContactId((contact.getContactId() != null) ? contact.getContactId().toBigInteger() : null);
        contacts.setEmail(contact.getEmail());
        contacts.setPhone(contact.getPhoneNumber());
        return contacts;

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
