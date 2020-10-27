package com.nttdata.quarkus.management.api.inventory;

import com.nttdata.quarkus.management.api.model.database.Inventories;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.math.BigInteger;
import java.util.List;

public class InventoriesRepository implements PanacheRepositoryBase<Inventories, BigInteger> {

    public List<Inventories> loadWithChildTables() {
        return this.find("from Inventories i " +
                                "left join fetch i.id " +
                                "left join fetch i.products " +
                                "left join fetch i.warehouses ").list();
    }
}
