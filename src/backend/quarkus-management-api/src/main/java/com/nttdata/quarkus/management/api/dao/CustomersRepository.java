package com.nttdata.quarkus.management.api.dao;

import com.nttdata.quarkus.management.api.model.database.Customers;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;
import java.math.BigInteger;

@Singleton
public class CustomersRepository implements PanacheRepositoryBase<Customers, BigInteger> {
}
