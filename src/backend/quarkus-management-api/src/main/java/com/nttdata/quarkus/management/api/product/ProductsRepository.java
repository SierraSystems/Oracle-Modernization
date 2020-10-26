package com.nttdata.quarkus.management.api.product;

import com.nttdata.pocdata.hibernate.Products;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;
import java.math.BigInteger;

@Singleton
public class ProductsRepository implements PanacheRepositoryBase<Products, BigInteger> {
}
