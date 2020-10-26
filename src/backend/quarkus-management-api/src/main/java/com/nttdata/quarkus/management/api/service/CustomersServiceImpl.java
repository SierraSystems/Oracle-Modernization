package com.nttdata.quarkus.management.api.service;

import com.nttdata.quarkus.management.api.dao.CustomersRepository;
import com.nttdata.quarkus.management.api.model.database.Customers;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
public class CustomersServiceImpl implements CustomersService {

    @Inject
    CustomersRepository customersRepository;

    @Override
    public List<Customers> getCustomers() {
        return customersRepository.findAll(Sort.by("CUSTOMER_ID").ascending()).list();
    }

    @Override
    public Customers getCustomer(BigInteger customersId) {
        return customersRepository.findById(customersId);
    }

    @Override
    public Customers updateCustomer(Customers customer) {

        //DO YOU LIKE MAGIC!!!!!!
        Customers entity = customersRepository.findById(customer.getCustomerId());

        entity.setName(customer.getName());
        entity.setAddress(customer.getAddress());
        entity.setWebsite(customer.getWebsite());
        entity.setCreditLimit(customer.getCreditLimit());

        return entity;

    }

    @Override
    public Customers addCustomer(Customers customer) {

        customer.setCustomerId(getNextId());
        customersRepository.persist(customer);
        return customer;

    }

    @Override
    public void deleteCustomer(BigInteger customerId) {

        Customers entity = customersRepository.findById(customerId);
        customersRepository.delete(entity);

    }

    private BigInteger getNextId() {

        Customers maxIdCustomer = customersRepository.findAll(Sort.by("CUSTOMER_ID").descending()).list().get(0);

        return  maxIdCustomer.getCustomerId().add(BigInteger.ONE);

    }
}
