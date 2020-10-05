package com.nttdata.managmenapi.controller;

import com.nttdata.managementapi.api.model.Contact;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    @GetMapping(value = "/management/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getTest() {
        Contact contact = new Contact();
        contact.setContactId(BigDecimal.ONE);
        contact.setCustomerId(BigDecimal.ONE);
        contact.setEmail("bobross@paintit.com");
        contact.setFirstName("Bob");
        contact.setLastName("Ross");

        Contact contact2 = new Contact();
        contact2.setContactId(BigDecimal.valueOf(2));
        contact2.setCustomerId(BigDecimal.valueOf(2));
        contact2.setEmail("hanSolo@paintit.com");
        contact2.setFirstName("Han");
        contact2.setLastName("Solo");

        return ResponseEntity.ok(Arrays.asList(contact, contact2));
    }

    @GetMapping(value = "/management/contact/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getContact(@PathVariable BigDecimal contactId) {
        Contact contact = new Contact();
        contact.setContactId(BigDecimal.ONE);
        contact.setCustomerId(BigDecimal.ONE);
        contact.setEmail("bobross@paintit.com");
        contact.setFirstName("Bob");
        contact.setLastName("Ross");
        return ResponseEntity.ok(contact);
    }

    @PostMapping(value = "/management/contact/update")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contact);
    }

    @PutMapping(value = "management/contact/add")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contact);
    }
}
