package com.nttdata.quarkus.management.api.controller;

import com.nttdata.quarkus.management.api.contact.ContactMapper;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@ApplicationScoped
public class ManagementApiImpl implements ManagementApi {


    private final ContactsService contactsService;
    private final CustomersService customersService;
    private final ContactMapper contactMapper;

    @Inject
    public ManagementApiImpl(ContactsService contactsService, CustomersService customersService, ContactMapper contactMapper) {
        this.contactsService = contactsService;
        this.customersService = customersService;
        this.contactMapper = contactMapper;
    }

    @Override
    @Transactional
    public Response addContact(@Valid Contact contact, SecurityContext securityContext) {

        Contacts newContact = contactMapper.toContacts(contact);
        return Response.ok(contactMapper.toContact(contactsService.addContact(newContact))).status(201).build();

    }

    @Override
    @Transactional
    public Response deleteContact(BigDecimal contactId, SecurityContext securityContext) {
        contactsService.deleteContact(contactId.toBigInteger());
        return Response.status(204).build();
    }

    @Override
    @Transactional
    public Response getContact(BigDecimal contactId, SecurityContext securityContext) {
        return Response.ok(contactMapper.toContact(contactsService.getContact(contactId.toBigInteger()))).build();
    }

    @Override
    @Transactional
    public Response getContacts(SecurityContext securityContext) {

        return Response.ok(
                contactsService
                        .getContacts()
                        .stream()
                        .map(contacts -> contactMapper.toContact(contacts))
                        .collect(Collectors.toList())).build();

    }

    @Override
    @Transactional
    public Response updateContact(@Valid Contact contact, SecurityContext securityContext) {

        Contacts updated = contactsService.updateContact(contactMapper.toContacts(contact));

        return Response.ok(contactMapper.toContact(updated)).build();

    }

    @Override
    @Transactional
    public Response addCustomer(@Valid Customer customer, SecurityContext securityContext) {
        System.out.println("Hello we have entered the add method");
        if (customer.getCustomerId() != null) {
            return Response.status(400).build();
        }
        return Response.ok(mapCustomer(customersService.addCustomer(mapCustomers(customer)))).status(200).build();
    }

    @Override
    @Transactional
    public Response deleteCustomer(BigDecimal customerId, SecurityContext securityContext) {
        customersService.deleteCustomer(customerId.toBigInteger());
        return Response.status(204).build();
    }

    @Override
    @Transactional
    public Response getCustomer(BigDecimal customerId, SecurityContext securityContext) {
        return Response.ok(mapCustomer(customersService.getCustomer(customerId.toBigInteger()))).build();
    }

    @Override
    @Transactional
    public Response getCustomers(SecurityContext securityContext) {
        return Response.ok(
                customersService.getCustomers().stream()
                .map(this::mapCustomer)
                .collect(Collectors.toList())).build();
    }

    @Override
    @Transactional
    public Response updateCustomer(@Valid Customer customer, SecurityContext securityContext) {

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

        System.out.println("Hello we are mapping a api customer to a database customer");
        Customer customer = new Customer();
        customer.setCustomerId(BigDecimal.valueOf(customers.getCustomerId().longValue()));
        customer.setFullName(customers.getName());
        customer.setAddress(customers.getAddress());
        customer.setWebsite(customers.getWebsite());
        customer.setCreditLimit(customers.getCreditLimit());
        return customer;

    }
}
