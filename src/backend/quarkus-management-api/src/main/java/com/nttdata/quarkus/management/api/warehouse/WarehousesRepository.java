package com.nttdata.quarkus.management.api.warehouse;

import com.nttdata.pocdata.hibernate.Warehouses;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;
import java.math.BigInteger;

@Singleton
public class WarehousesRepository implements PanacheRepositoryBase<Warehouses, BigInteger> {
}
