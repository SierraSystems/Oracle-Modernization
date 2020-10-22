package com.nttdata.quarkus.management.api.controller;

import com.nttdata.quarkus.management.api.model.api.Contact;
import com.nttdata.quarkus.management.api.model.api.Customer;
import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.model.database.Customers;
import com.nttdata.quarkus.management.api.service.CustomersService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Path("/management")
@ApplicationScoped
public class CustomersResource {

    @Inject
    CustomersService customersService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    @Transactional
    public List<Customer> getCustomers() {

        return customersService.getCustomers().stream()
                .map(this::mapCustomer)
                .collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/{customerId}")
    @Transactional
    public Customer getCustomer(@PathParam BigInteger customerId) {
        return mapCustomer(customersService.getCustomer(customerId));
    }

    @POST
    @Path("/customers/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Customer customer) {

        if (customer.getCustomerId() != null) {
            throw new WebApplicationException("Id not required.", 422);
        }

        return Response.ok(customersService.addCustomer(mapCustomers(customer))).status(201).build();
    }

    @PUT
    @Path("/customers/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Customer customer) {
        return Response.ok(customersService.updateCustomer(mapCustomers(customer))).status(200).build();
    }

    @DELETE
    @Path("/customers/{customerId}/delete")
    @RolesAllowed("nope")
    @Transactional
    public Response delete(@PathParam BigInteger customerId) {
        customersService.deleteCustomer(customerId);
        return Response.status(204).build();
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
