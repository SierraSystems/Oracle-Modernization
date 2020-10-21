package com.nttdata.quarkus.management.api.model.database;
// Generated Oct 20, 2020 7:29:03 AM by Hibernate Tools 6.0.0.Alpha2


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name="ORDERS"
)
public class Orders  extends PanacheEntityBase implements java.io.Serializable {


     private BigInteger orderId;
     private Customers customers;
     private Employees employees;
     private String status;
     private Date orderDate;
     private Set<OrderItems> orderItemses = new HashSet<OrderItems>(0);

    public Orders() {
    }

	
    public Orders(BigInteger orderId, Customers customers, String status, Date orderDate) {
        this.orderId = orderId;
        this.customers = customers;
        this.status = status;
        this.orderDate = orderDate;
    }
    public Orders(BigInteger orderId, Customers customers, Employees employees, String status, Date orderDate, Set<OrderItems> orderItemses) {
       this.orderId = orderId;
       this.customers = customers;
       this.employees = employees;
       this.status = status;
       this.orderDate = orderDate;
       this.orderItemses = orderItemses;
    }
   
     @Id 

    
    @Column(name="ORDER_ID", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    public Customers getCustomers() {
        return this.customers;
    }
    
    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SALESMAN_ID")
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    
    @Column(name="STATUS", nullable=false, length=20)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="ORDER_DATE", nullable=false, length=7)
    public Date getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    public Set<OrderItems> getOrderItemses() {
        return this.orderItemses;
    }
    
    public void setOrderItemses(Set<OrderItems> orderItemses) {
        this.orderItemses = orderItemses;
    }




}


