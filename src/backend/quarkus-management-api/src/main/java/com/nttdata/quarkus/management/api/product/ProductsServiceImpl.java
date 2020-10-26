package com.nttdata.quarkus.management.api.product;

import com.nttdata.pocdata.hibernate.Products;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    @Inject
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> getProducts() {
        return productsRepository.listAll(Sort.ascending("PRODUCT_NAME"));
    }

    @Override
    public Products getProduct(BigInteger productId) {
        return productsRepository.findById(productId);
    }

    @Override
    public Products updateProduct(Products products) {
        this.productsRepository.getEntityManager().merge(products);
        return products;
    }

    @Override
    public Products addProduct(Products products) {
        productsRepository.persist(products);
        return products;
    }

    @Override
    public void deleteProduct(BigInteger productId) {
        productsRepository.deleteById(productId);
    }


}
