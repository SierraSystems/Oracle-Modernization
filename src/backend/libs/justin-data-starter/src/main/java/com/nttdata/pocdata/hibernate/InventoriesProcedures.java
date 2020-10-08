package com.nttdata.pocdata.hibernate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "WAREHOUSESTOCK")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "findStockByProductLocation",
                procedureName = "GET_WAREHOUSE_STOCK",
                resultClasses = { InventoriesProcedures.class },
                parameters = {
                        @StoredProcedureParameter(
                                name = "IN_PRODUCT_ID",
                                type = BigInteger.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "IN_WAREHOUSE_ID",
                                type = BigInteger.class,
                                mode = ParameterMode.IN),
                        @StoredProcedureParameter(
                                name = "INVENTORY_RESULT",
                                type = Integer.class,
                                mode = ParameterMode.OUT)})
})
public class InventoriesProcedures extends Inventories {
    private int inventory_result;

    @Column(name="INVENTORY_RESULT", nullable=true, precision=8, scale=0)
    public int getInventory() {
        return this.inventory_result;
    }

    public void setInventory(int inventory_result) {
        this.inventory_result = inventory_result;
    }

}
