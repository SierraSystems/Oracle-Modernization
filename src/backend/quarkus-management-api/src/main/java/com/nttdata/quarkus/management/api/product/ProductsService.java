package com.nttdata.quarkus.management.api.product;

import com.nttdata.pocdata.hibernate.Products;

import java.math.BigInteger;
import java.util.List;

public interface ProductsService {
    List<Products> getProducts();

    Products getProduct(BigInteger productId);

    Products updateProduct(Products products);

    Products addProduct(Products products);

    void deleteProduct(BigInteger productId);
}
