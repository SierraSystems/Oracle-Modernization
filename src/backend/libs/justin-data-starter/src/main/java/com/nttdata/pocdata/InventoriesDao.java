package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.InventoriesProcedures;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Transactional
public interface InventoriesDao extends CrudRepository<InventoriesProcedures, Integer> {

    @Procedure(name = "findStockByProductLocation")
    Integer findStockByProductLocation(@Param("IN_PRODUCT_ID") BigInteger productId, @Param("IN_WAREHOUSE_ID") BigInteger warehouseId);

}
