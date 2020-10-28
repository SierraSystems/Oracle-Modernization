package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.openapi.model.Contact;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ContactDto extends Contact {

    public ContactDto(BigInteger contactId, String firstName, String lastName, String email, String phoneNumber, BigInteger customerId, String customerName) {
        this.setContactId(new BigDecimal(contactId));
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setCustomerId(new BigDecimal(customerId));
        this.setCustomerName(customerName);
    }
}
