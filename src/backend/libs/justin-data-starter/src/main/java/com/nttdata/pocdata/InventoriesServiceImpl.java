package com.nttdata.pocdata;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class InventoriesServiceImpl implements InventoriesService {

    private final InventoriesDao inventoriesDao;

    public InventoriesServiceImpl(InventoriesDao inventoriesDao) {
        this.inventoriesDao = inventoriesDao;
    }


    @Override
    public int getStockForLocationAndType(BigInteger productId, BigInteger warehouseId) {
        return inventoriesDao.findStockByProductLocation(productId, warehouseId);
    }
}
