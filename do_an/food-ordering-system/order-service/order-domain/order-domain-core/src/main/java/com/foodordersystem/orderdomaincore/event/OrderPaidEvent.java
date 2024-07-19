package com.foodordersystem.orderdomaincore.event;

import com.foodordersystem.orderdomaincore.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
