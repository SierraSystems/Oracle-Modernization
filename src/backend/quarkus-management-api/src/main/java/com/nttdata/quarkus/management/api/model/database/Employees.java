package com.nttdata.quarkus.management.api.model.database;
// Generated Oct 27, 2020 8:51:28 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employees generated by hbm2java
 */
@Entity
@Table(name="EMPLOYEES"
)
public class Employees  implements java.io.Serializable {


     private BigInteger employeeId;
     private Employees employees;
     private String firstName;
     private String lastName;
     private String email;
     private String phone;
     private Date hireDate;
     private String jobTitle;
     private Set<Orders> orderses = new HashSet<Orders>(0);
     private Set<Employees> employeeses = new HashSet<Employees>(0);

    public Employees() {
    }

	
    public Employees(BigInteger employeeId, String firstName, String lastName, String email, String phone, Date hireDate, String jobTitle) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
    }
    public Employees(BigInteger employeeId, Employees employees, String firstName, String lastName, String email, String phone, Date hireDate, String jobTitle, Set<Orders> orderses, Set<Employees> employeeses) {
       this.employeeId = employeeId;
       this.employees = employees;
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.phone = phone;
       this.hireDate = hireDate;
       this.jobTitle = jobTitle;
       this.orderses = orderses;
       this.employeeses = employeeses;
    }
   
     @Id 

    
    @Column(name="EMPLOYEE_ID", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MANAGER_ID")
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    
    @Column(name="FIRST_NAME", nullable=false, length=255)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="LAST_NAME", nullable=false, length=255)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="EMAIL", nullable=false, length=255)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="PHONE", nullable=false, length=50)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="HIRE_DATE", nullable=false, length=7)
    public Date getHireDate() {
        return this.hireDate;
    }
    
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    
    @Column(name="JOB_TITLE", nullable=false, length=255)
    public String getJobTitle() {
        return this.jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
    public Set<Orders> getOrderses() {
        return this.orderses;
    }
    
    public void setOrderses(Set<Orders> orderses) {
        this.orderses = orderses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="employees")
    public Set<Employees> getEmployeeses() {
        return this.employeeses;
    }
    
    public void setEmployeeses(Set<Employees> employeeses) {
        this.employeeses = employeeses;
    }




}


