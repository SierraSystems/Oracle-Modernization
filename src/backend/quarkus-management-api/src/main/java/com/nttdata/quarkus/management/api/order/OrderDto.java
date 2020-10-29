package com.nttdata.quarkus.management.api.order;

import com.nttdata.quarkus.management.api.openapi.model.Order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDto extends Order {

    public OrderDto(BigInteger orderId, Date date, BigInteger customerId, String customerName, BigInteger employeeId, String employeeName, String status, BigDecimal totalAmount, BigDecimal totalItems) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        this.setOrderId(new BigDecimal(orderId));
        this.setCustomerId(new BigDecimal(customerId));
        this.setCustomerName(customerName);
        this.setDate(formatter.format(date));
        if(employeeId != null) this.setEmployeeId(new BigDecimal(employeeId));
        this.setEmployeeName(employeeName);
        this.setStatus(StatusEnum.valueOf(status.toUpperCase()));
        this.setTotalAmount(totalAmount);
        this.setTotalItems(totalItems);
    }

}
