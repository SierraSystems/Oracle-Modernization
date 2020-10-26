package com.nttdata.quarkus.management.api.employee;

import com.nttdata.pocdata.hibernate.Employees;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;
import java.math.BigInteger;

@Singleton
public class EmployeesRepository implements PanacheRepositoryBase<Employees, BigInteger> {
}
