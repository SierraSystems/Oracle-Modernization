package com.nttdata.pocdata;

import java.math.BigInteger;

public interface InventoriesService {
    int getStockForLocationAndType(BigInteger productId, BigInteger warehouseId);
}
