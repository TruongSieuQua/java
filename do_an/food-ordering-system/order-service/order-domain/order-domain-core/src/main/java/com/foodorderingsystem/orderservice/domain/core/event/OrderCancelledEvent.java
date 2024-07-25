package com.foodorderingsystem.orderservice.domain.core.event;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
