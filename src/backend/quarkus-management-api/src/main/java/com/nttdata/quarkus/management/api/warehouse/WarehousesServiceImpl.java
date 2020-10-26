package com.nttdata.quarkus.management.api.warehouse;

import com.nttdata.pocdata.hibernate.Countries;
import com.nttdata.pocdata.hibernate.Warehouses;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class WarehousesServiceImpl implements WarehousesService {

    private final WarehousesRepository warehousesRepository;

    @Inject
    public WarehousesServiceImpl(WarehousesRepository warehousesRepository) {
        this.warehousesRepository = warehousesRepository;
    }

    @Override
    public List<Warehouses> getWarehouses() {
        return warehousesRepository.listAll(Sort.ascending("WAREHOUSE_NAME"));
    }

    @Override
    public Warehouses getWarehouse(BigInteger warehouseId) {
        return warehousesRepository.findById(warehouseId);
    }

    @Override
    public Warehouses updateWarehouse(Warehouses warehouses) {
        this.warehousesRepository.getEntityManager().merge(warehouses);
        return warehouses;
    }

    @Override
    public Warehouses addWarehouse(Warehouses warehouses) {
        warehousesRepository.persist(warehouses);
        return warehouses;
    }

    @Override
    public void deleteWarehouse(BigInteger warehouseId) {
        warehousesRepository.deleteById(warehouseId);
    }


}
