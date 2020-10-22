package com.nttdata.quarkus.management.api.controller;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.model.database.Customers;
import com.nttdata.quarkus.management.api.openapi.ManagementApi;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import com.nttdata.quarkus.management.api.openapi.model.Customer;
import com.nttdata.quarkus.management.api.service.ContactsService;
import com.nttdata.quarkus.management.api.service.CustomersService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class ManagementApiImpl implements ManagementApi {

    @Inject
    ContactsService contactsService;

    @Inject
    CustomersService customersService;

    private static final Logger logger = Logger.getLogger(String.valueOf(ManagementApiImpl.class));

    @Override
    @Transactional
    public Response addContact(@Valid Contact contact, SecurityContext securityContext) {

        logger.info("Add contact");
        return Response.ok(mapContact(contactsService.addContact(mapContacts(contact)))).status(201).build();

    }

    @Override
    @Transactional
    public Response deleteContact(BigDecimal contactId, SecurityContext securityContext) {

        logger.info("Delete contact");
        contactsService.deleteContact(contactId.toBigInteger());
        return Response.status(204).build();

    }

    @Override
    @Transactional
    public Response getContact(BigDecimal contactId, SecurityContext securityContext) {

        logger.info("Get contact");
        return Response.ok(mapContact(contactsService.getContact(contactId.toBigInteger()))).build();

    }

    @Override
    @Transactional
    public Response getContacts(SecurityContext securityContext) {

        logger.info("Add contacts");
        return Response.ok(
                contactsService
                        .getContacts()
                        .stream()
                        .map(this::mapContact)
                        .collect(Collectors.toList())).build();

    }

    @Override
    @Transactional
    public Response updateContact(@Valid Contact contact, SecurityContext securityContext) {

        logger.info("Update contact");
        return Response.ok(mapContact(contactsService.updateContact(mapContacts(contact)))).status(200).build();

    }

    private Contact mapContact(Contacts contacts) {

        Contact contact = new Contact();
        contact.setLastName(contacts.getLastName());
        contact.setFirstName(contacts.getFirstName());
        contact.setContactId(BigDecimal.valueOf(contacts.getContactId().longValue()));
        contact.setEmail(contacts.getEmail());
        contact.setPhoneNumber(contacts.getPhone());
        if (contacts.getCustomers() != null && contacts.getCustomers().getCustomerId() != null)
            contact.setCustomerId(BigDecimal.valueOf(contacts.getCustomers().getCustomerId().longValue()));
        return contact;

    }

    private Contacts mapContacts(Contact contact) {

        Contacts contacts = new Contacts();

        if(contact.getContactId() != null)
            contacts.setContactId(contact.getContactId().toBigInteger());

        contacts.setLastName(contact.getLastName());
        contacts.setFirstName(contact.getFirstName());
        contacts.setEmail(contact.getEmail());
        contacts.setPhone(contact.getPhoneNumber());

        return contacts;

    }

    @Override
    @Transactional
    public Response addCustomer(@Valid Customer customer, SecurityContext securityContext) {

        logger.info("Add customer");
        if (customer.getCustomerId() != null) {
            throw new WebApplicationException("Id not required.", 422);
        }
        return Response.ok(mapCustomer(customersService.addCustomer(mapCustomers(customer)))).status(200).build();

    }

    @Override
    @Transactional
    public Response deleteCustomer(BigDecimal customerId, SecurityContext securityContext) {

        logger.info("Delete customer");
        customersService.deleteCustomer(customerId.toBigInteger());
        return Response.status(204).build();

    }

    @Override
    @Transactional
    public Response getCustomer(BigDecimal customerId, SecurityContext securityContext) {

        logger.info("Get customer");
        return Response.ok(mapCustomer(customersService.getCustomer(customerId.toBigInteger()))).build();

    }

    @Override
    @Transactional
    public Response getCustomers(SecurityContext securityContext) {

        logger.info("Get customers");
        return Response.ok(
                customersService.getCustomers().stream()
                .map(this::mapCustomer)
                .collect(Collectors.toList())).build();

    }

    @Override
    @Transactional
    public Response updateCustomer(@Valid Customer customer, SecurityContext securityContext) {

        logger.info("Update customer");
        return Response.ok(mapCustomer(customersService.updateCustomer(mapCustomers(customer)))).status(200).build();

    }

    private Customers mapCustomers(Customer customer) {

        Customers customers = new Customers();
        customers.setCustomerId((customer.getCustomerId() != null) ? customer.getCustomerId().toBigInteger() : null);
        customers.setName(customer.getFullName());
        customers.setAddress(customer.getAddress());
        customers.setWebsite(customer.getWebsite());
        customers.setCreditLimit(customer.getCreditLimit());
        return customers;

    }

    private Customer mapCustomer(Customers customers) {

        Customer customer = new Customer();
        customer.setCustomerId(BigDecimal.valueOf(customers.getCustomerId().longValue()));
        customer.setFullName(customers.getName());
        customer.setAddress(customers.getAddress());
        customer.setWebsite(customers.getWebsite());
        customer.setCreditLimit(customers.getCreditLimit());
        return customer;

    }
}
