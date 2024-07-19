package com.foodordersystem.orderdomaincore;

import com.foodordersystem.orderdomaincore.entity.Order;
import com.foodordersystem.orderdomaincore.entity.Restaurant;
import com.foodordersystem.orderdomaincore.event.OrderCancelledEvent;
import com.foodordersystem.orderdomaincore.event.OrderCreatedEvent;
import com.foodordersystem.orderdomaincore.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
