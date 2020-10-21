package com.nttdata.quarkus.management.api.service;

import com.nttdata.quarkus.management.api.dao.CustomersDao;
import com.nttdata.quarkus.management.api.model.database.Customers;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.math.BigInteger;
import java.util.List;

public class CustomersServiceImpl implements CustomersService {

    @Inject
    CustomersDao customersDao;

    @Override
    public List<Customers> getCustomers() {
        return customersDao.findAll(Sort.by("CUSTOMER_ID").ascending()).list();
    }

    @Override
    public Customers getCustomer(BigInteger customersId) {
        return customersDao.findById(customersId);
    }

    @Override
    public Customers updateCustomer(Customers customer) {

        //DO YOU LIKE MAGIC!!!!!!
        Customers entity = customersDao.findById(customer.getCustomerId());

        entity.setName(customer.getName());
        entity.setAddress(customer.getAddress());
        entity.setWebsite(customer.getWebsite());
        entity.setCreditLimit(customer.getCreditLimit());

        return entity;

    }

    @Override
    public Customers addCustomer(Customers customer) {

        customer.setCustomerId(getNextId());
        customersDao.persist(customer);
        return customer;

    }

    @Override
    public void deleteCustomer(BigInteger customerId) {

        Customers entity = customersDao.findById(customerId);
        if (entity == null) {
            throw new WebApplicationException("Customer does not exist.", 404);
        }
        customersDao.delete(entity);

    }

    private BigInteger getNextId() {

        Customers maxIdCustomer = customersDao.findAll(Sort.by("CUSTOMER_ID").descending()).list().get(0);

        return  maxIdCustomer.getCustomerId().add(BigInteger.ONE);

    }
}
