package com.nttdata.quarkus.management.api.employee;

import com.nttdata.quarkus.management.api.model.database.Employees;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    @Inject
    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public List<Employees> getEmployees() {
        return employeesRepository.listAll(Sort.ascending("LAST_NAME"));
    }

    @Override
    public Employees getEmployee(BigInteger employeeId) {
        return employeesRepository.findById(employeeId);
    }

    @Override
    public Employees updateEmployee(Employees employee) {
        this.employeesRepository.getEntityManager().merge(employee);
        return employee;
    }

    @Override
    public Employees addEmployee(Employees employee) {
        employee.setEmployeeId(getNextId());
        employeesRepository.persist(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(BigInteger employeeId) {
        employeesRepository.deleteById(employeeId);
    }

    private BigInteger getNextId() {

        Employees maxIdEmployee = employeesRepository.listAll(Sort.by("EMPLOYEE_ID").descending()).get(0);

        return  maxIdEmployee.getEmployeeId().add(BigInteger.ONE);

    }
}
