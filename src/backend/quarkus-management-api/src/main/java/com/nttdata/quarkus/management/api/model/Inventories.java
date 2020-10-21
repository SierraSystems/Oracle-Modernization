package com.nttdata.quarkus.management.api.model;
// Generated Oct 20, 2020 7:29:03 AM by Hibernate Tools 6.0.0.Alpha2


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

/**
 * Inventories generated by hbm2java
 */
@Entity
@Table(name="INVENTORIES"
)
public class Inventories  extends PanacheEntityBase implements java.io.Serializable {


     private InventoriesId id;
     private Warehouses warehouses;
     private Products products;
     private int quantity;

    public Inventories() {
    }

    public Inventories(InventoriesId id, Warehouses warehouses, Products products, int quantity) {
       this.id = id;
       this.warehouses = warehouses;
       this.products = products;
       this.quantity = quantity;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="productId", column=@Column(name="PRODUCT_ID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="warehouseId", column=@Column(name="WAREHOUSE_ID", nullable=false, precision=12, scale=0) ) } )
    public InventoriesId getId() {
        return this.id;
    }
    
    public void setId(InventoriesId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WAREHOUSE_ID", nullable=false, insertable=false, updatable=false)
    public Warehouses getWarehouses() {
        return this.warehouses;
    }
    
    public void setWarehouses(Warehouses warehouses) {
        this.warehouses = warehouses;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID", nullable=false, insertable=false, updatable=false)
    public Products getProducts() {
        return this.products;
    }
    
    public void setProducts(Products products) {
        this.products = products;
    }

    
    @Column(name="QUANTITY", nullable=false, precision=8, scale=0)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}


