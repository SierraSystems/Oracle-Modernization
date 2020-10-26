package com.nttdata.quarkus.management.api.inventory;

import com.nttdata.pocdata.hibernate.Inventories;

import java.math.BigInteger;
import java.util.List;

public interface InventoriesService {
    List<Inventories> getInventories();

    Inventories getInventory(BigInteger inventoryId);

    Inventories updateInventory(Inventories inventories);

    Inventories addInventory(Inventories inventories);

    void deleteInventory(BigInteger inventoryId);
}
