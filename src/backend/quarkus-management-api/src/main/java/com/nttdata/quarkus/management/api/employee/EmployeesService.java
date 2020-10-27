package com.nttdata.quarkus.management.api.employee;

import com.nttdata.quarkus.management.api.model.database.Employees;

import java.math.BigInteger;
import java.util.List;

public interface EmployeesService {
    List<Employees> getEmployees();

    Employees getEmployee(BigInteger employeeId);

    Employees updateEmployee(Employees employees);

    Employees addEmployee(Employees employees);

    void deleteEmployee(BigInteger employeeId);
}
