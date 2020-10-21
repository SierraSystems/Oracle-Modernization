package com.nttdata.quarkus.management.api.model.database;
// Generated Oct 20, 2020 7:29:03 AM by Hibernate Tools 6.0.0.Alpha2


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Products generated by hbm2java
 */
@Entity
@Table(name="PRODUCTS"
)
public class Products  extends PanacheEntityBase implements java.io.Serializable {


     private BigInteger productId;
     private ProductCategories productCategories;
     private String productName;
     private String description;
     private BigDecimal standardCost;
     private BigDecimal listPrice;
     private Set<Inventories> inventorieses = new HashSet<Inventories>(0);
     private Set<OrderItems> orderItemses = new HashSet<OrderItems>(0);

    public Products() {
    }

	
    public Products(BigInteger productId, ProductCategories productCategories, String productName) {
        this.productId = productId;
        this.productCategories = productCategories;
        this.productName = productName;
    }
    public Products(BigInteger productId, ProductCategories productCategories, String productName, String description, BigDecimal standardCost, BigDecimal listPrice, Set<Inventories> inventorieses, Set<OrderItems> orderItemses) {
       this.productId = productId;
       this.productCategories = productCategories;
       this.productName = productName;
       this.description = description;
       this.standardCost = standardCost;
       this.listPrice = listPrice;
       this.inventorieses = inventorieses;
       this.orderItemses = orderItemses;
    }
   
     @Id 

    
    @Column(name="PRODUCT_ID", unique=true, nullable=false, precision=22, scale=0)
    public BigInteger getProductId() {
        return this.productId;
    }
    
    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID", nullable=false)
    public ProductCategories getProductCategories() {
        return this.productCategories;
    }
    
    public void setProductCategories(ProductCategories productCategories) {
        this.productCategories = productCategories;
    }

    
    @Column(name="PRODUCT_NAME", nullable=false, length=255)
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    @Column(name="DESCRIPTION", length=2000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="STANDARD_COST", precision=9, scale=2)
    public BigDecimal getStandardCost() {
        return this.standardCost;
    }
    
    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    
    @Column(name="LIST_PRICE", precision=9, scale=2)
    public BigDecimal getListPrice() {
        return this.listPrice;
    }
    
    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="products")
    public Set<Inventories> getInventorieses() {
        return this.inventorieses;
    }
    
    public void setInventorieses(Set<Inventories> inventorieses) {
        this.inventorieses = inventorieses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="products")
    public Set<OrderItems> getOrderItemses() {
        return this.orderItemses;
    }
    
    public void setOrderItemses(Set<OrderItems> orderItemses) {
        this.orderItemses = orderItemses;
    }




}

