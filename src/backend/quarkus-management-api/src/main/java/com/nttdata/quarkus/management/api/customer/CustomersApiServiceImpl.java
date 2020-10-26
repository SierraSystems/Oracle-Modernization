package com.nttdata.quarkus.management.api.customer;

import com.nttdata.pocdata.hibernate.Customers;
import com.nttdata.quarkus.management.api.openapi.CustomersApi;
import com.nttdata.quarkus.management.api.openapi.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@ApplicationScoped
public class CustomersApiServiceImpl implements CustomersApi {

    @Inject
    CustomersService customersService;

    private static final Logger logger = Logger.getLogger(String.valueOf(CustomersApiServiceImpl.class));

    @Override
    @Transactional
    public Response addCustomer(@Valid Customer customer, SecurityContext securityContext) {

        logger.info("Add customer");
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
