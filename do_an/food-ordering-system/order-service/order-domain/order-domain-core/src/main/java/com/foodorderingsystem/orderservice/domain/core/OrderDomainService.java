package com.foodorderingsystem.orderservice.domain.core;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.orderservice.domain.core.entity.Restaurant;
import com.foodorderingsystem.orderservice.domain.core.event.OrderCancelledEvent;
import com.foodorderingsystem.orderservice.domain.core.event.OrderCreatedEvent;
import com.foodorderingsystem.orderservice.domain.core.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
