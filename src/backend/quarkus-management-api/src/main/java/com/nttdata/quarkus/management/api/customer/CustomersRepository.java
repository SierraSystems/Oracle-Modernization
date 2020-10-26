package com.nttdata.quarkus.management.api.customer;

import com.nttdata.pocdata.hibernate.Customers;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;
import java.math.BigInteger;

@Singleton
public class CustomersRepository implements PanacheRepositoryBase<Customers, BigInteger> {
}
