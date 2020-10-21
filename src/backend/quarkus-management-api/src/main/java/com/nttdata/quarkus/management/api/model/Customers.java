package com.nttdata.quarkus.management.api.model;
// Generated Oct 20, 2020 7:29:03 AM by Hibernate Tools 6.0.0.Alpha2


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Customers generated by hbm2java
 */
@Entity
@Table(name="CUSTOMERS"
)
public class Customers  extends PanacheEntityBase implements java.io.Serializable {


     private BigInteger customerId;
     private String name;
     private String address;
     private String website;
     private BigDecimal creditLimit;
     private Set<Orders> orderses = new HashSet<Orders>(0);
     private Set<Contacts> contactses = new HashSet<Contacts>(0);

    public Customers() {
    }

	
    public Customers(BigInteger customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
    public Customers(BigInteger customerId, String name, String address, String website, BigDecimal creditLimit, Set<Orders> orderses, Set<Contacts> contactses) {
       this.customerId = customerId;
       this.name = name;
       this.address = address;
       this.website = website;
       this.creditLimit = creditLimit;
       this.orderses = orderses;
       this.contactses = contactses;
    }
   
     @Id 

    
    @Column(name="CUSTOMER_ID", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    
    @Column(name="NAME", nullable=false, length=255)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="ADDRESS", length=255)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    
    @Column(name="WEBSITE", length=255)
    public String getWebsite() {
        return this.website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }

    
    @Column(name="CREDIT_LIMIT", precision=8, scale=2)
    public BigDecimal getCreditLimit() {
        return this.creditLimit;
    }
    
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customers")
    public Set<Orders> getOrderses() {
        return this.orderses;
    }
    
    public void setOrderses(Set<Orders> orderses) {
        this.orderses = orderses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="customers")
    public Set<Contacts> getContactses() {
        return this.contactses;
    }
    
    public void setContactses(Set<Contacts> contactses) {
        this.contactses = contactses;
    }




}


