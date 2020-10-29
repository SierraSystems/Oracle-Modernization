package com.nttdata.quarkus.management.api.order;

import com.nttdata.quarkus.management.api.model.database.OrderItems;
import com.nttdata.quarkus.management.api.model.database.OrderItemsId;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

import javax.inject.Singleton;
import java.math.BigInteger;
import java.util.List;

@Singleton
public class OrderRepository implements PanacheRepositoryBase<OrderItems, OrderItemsId> {

    private static final String BASE_QUERY =
            "SELECT " +
            " new com.nttdata.quarkus.management.api.order.OrderDto(" +
            "   ord.orderId" +
            ",  ord.orderDate" +
            ",  cus.customerId" +
            ",  cus.name" +
            ",  emp.employeeId" +
            ",  concat(emp.firstName, ' ', emp.lastName)" +
            ",  ord.status" +
            ",  sum(it.unitPrice * it.quantity)" +
            ",  sum(it.quantity))" +
            " FROM OrderItems it" +
            " LEFT JOIN it.orders ord" +
            " LEFT JOIN ord.customers cus" +
            " LEFT JOIN ord.employees emp";
     private static final String GROUP_BY =
             " GROUP BY " +
             "   ord.orderId" +
             ",  ord.orderDate" +
             ",  cus.customerId" +
             ",  cus.name" +
             ",  emp.employeeId" +
             ",  emp.firstName" +
             ",  emp.lastName" +
             ",  ord.status ";
    private static final String ORDER_BY_CLAUSE = "ord.orderId";
    private static final Sort DESCENDING = Sort.descending(ORDER_BY_CLAUSE);
    public static final String ID = "id";

    public List<OrderDto> getOrders(BigInteger orderId, int limit) {

        StringBuilder query = new StringBuilder();
        query.append(BASE_QUERY);
        if(isDefault(orderId)) query.append(" where ord.orderId <= : " + ID);
        query.append(GROUP_BY);


        PanacheQuery panacheQuery = isDefault(orderId) ?  this
                    .find(query.toString(), DESCENDING,  Parameters.with(ID, orderId).map()) :
                    this.find(query.toString(), DESCENDING);

        return panacheQuery.page(Page.ofSize(limit)).list();

    }

    private boolean isDefault(BigInteger orderId) {
        return orderId.compareTo(BigInteger.ZERO) == 1;
    }

}
