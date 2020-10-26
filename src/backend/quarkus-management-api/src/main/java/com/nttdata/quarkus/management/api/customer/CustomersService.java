package com.nttdata.quarkus.management.api.customer;

import com.nttdata.pocdata.hibernate.Customers;

import java.math.BigInteger;
import java.util.List;

public interface CustomersService {
    List<Customers> getCustomers();

    Customers getCustomer(BigInteger customersId);

    Customers updateCustomer(Customers customer);

    Customers addCustomer(Customers customer);

    void deleteCustomer(BigInteger customersId);
}
