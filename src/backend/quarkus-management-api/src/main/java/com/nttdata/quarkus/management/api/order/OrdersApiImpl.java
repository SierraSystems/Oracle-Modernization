package com.nttdata.quarkus.management.api.order;

import com.nttdata.quarkus.management.api.openapi.OrdersApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@ApplicationScoped
public class OrdersApiImpl implements OrdersApi {

    private final OrderService orderService;

    @Inject
    public OrdersApiImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Response getOrders(@Min(1) @Max(100) Integer limit, String fromcursor, SecurityContext securityContext) {
        return Response.ok(orderService.getOrders(fromcursor, limit)).build();
    }

}
