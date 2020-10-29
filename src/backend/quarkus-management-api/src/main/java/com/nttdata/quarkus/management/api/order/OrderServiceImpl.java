package com.nttdata.quarkus.management.api.order;

import com.nttdata.quarkus.management.api.common.QueryHelpers;
import com.nttdata.quarkus.management.api.openapi.model.ListMetadata;
import com.nttdata.quarkus.management.api.openapi.model.Order;
import com.nttdata.quarkus.management.api.openapi.model.OrderList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.List;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    public static final String ORDER_KEY = "order";
    private final OrderRepository orderRepository;

    @Inject
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderList getOrders(String fromCursor, int limit) {

        OrderList result = new OrderList();

        BigInteger fromOrderId = QueryHelpers.getId(fromCursor, ORDER_KEY);

        int queryLimit = limit + 1;

        List<OrderDto> orders = orderRepository.getOrders(fromOrderId, queryLimit);

        if(orders.size() == queryLimit) {
            ListMetadata metadata = new ListMetadata();
            String nextCursor = MessageFormat.format("{0}:{1}", ORDER_KEY, orders.get(limit).getOrderId().toString());
            metadata.setNextCursor(Base64.getEncoder().encodeToString(nextCursor.getBytes()));
            result.setMetadata(metadata);
            orders.remove(limit);
        }

        result.setItems((List<Order>)(List<?>)orders);

        return result;

    }

}
