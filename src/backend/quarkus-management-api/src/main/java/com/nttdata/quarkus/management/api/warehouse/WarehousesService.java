package com.nttdata.quarkus.management.api.warehouse;

import com.nttdata.pocdata.hibernate.Warehouses;

import java.math.BigInteger;
import java.util.List;

public interface WarehousesService {
    List<Warehouses> getWarehouses();

    Warehouses getWarehouse(BigInteger warehouseId);

    Warehouses updateWarehouse(Warehouses warehouses);

    Warehouses addWarehouse(Warehouses warehouses);

    void deleteWarehouse(BigInteger warehouseId);
}
