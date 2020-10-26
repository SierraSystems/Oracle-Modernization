package com.nttdata.quarkus.management.api.inventory;

import com.nttdata.pocdata.hibernate.Employees;
import com.nttdata.pocdata.hibernate.Inventories;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class InventoriesServiceImpl implements InventoriesService {

private final InventoriesRepository inventoriesRepository;

    @Inject
    public InventoriesServiceImpl(InventoriesRepository inventoriesRepository) {
        this.inventoriesRepository = inventoriesRepository;
    }

    @Override
    public List<Inventories> getInventories() {
        return inventoriesRepository.loadWithChildTables();
    }

    @Override
    public Inventories getInventory(BigInteger inventoryId) {
        return inventoriesRepository.loadWithChildTables()
                .stream()
                .filter(inventories -> inventories.getId().equals(inventoryId))
                .findFirst()
                .get();
    }

    @Override
    public Inventories updateInventory(Inventories inventories) {
        inventoriesRepository.getEntityManager().merge(inventories);
        return inventories;
    }

    @Override
    public Inventories addInventory(Inventories inventories) {
        inventoriesRepository.persist(inventories);
        return inventories;
    }

    @Override
    public void deleteInventory(BigInteger inventoryId) {
        inventoriesRepository.deleteById(inventoryId);
    }

}
