package com.nttdata.managmenapi;

import com.nttdata.managementapi.api.ManagmentApiDelegate;
import com.nttdata.managementapi.api.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ManagementApiDelegateImpl implements ManagmentApiDelegate {
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
        Contact contact = new Contact();
        contact.setContactId(BigDecimal.ONE);
        contact.setCustomerId(BigDecimal.ONE);
        contact.setEmail("bobross@paintit.com");
        contact.setFirstName("Bob");
        contact.setLastName("Ross");
        contact.setPhoneNumber("123-456-7890");

        Contact contact2 = new Contact();
        contact.setContactId(BigDecimal.valueOf(2));
        contact.setCustomerId(BigDecimal.valueOf(2));
        contact.setEmail("hanSolo@paintit.com");
        contact.setFirstName("Han");
        contact.setLastName("Solo");
        contact.setPhoneNumber("987-654-3210");

        return ResponseEntity.ok(Arrays.asList(contact, contact2));
    }

    @Override
    public ResponseEntity<Contact> updateContact(Contact contact) {
        return ResponseEntity.ok(new Contact());
    }
}
