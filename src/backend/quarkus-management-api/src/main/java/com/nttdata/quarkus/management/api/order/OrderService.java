package com.nttdata.quarkus.management.api.order;

import com.nttdata.quarkus.management.api.openapi.model.OrderList;

public interface OrderService {

    OrderList getOrders(String fromCursor, int limit);
}
