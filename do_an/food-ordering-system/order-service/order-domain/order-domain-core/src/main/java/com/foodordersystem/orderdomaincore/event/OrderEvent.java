package com.foodordersystem.orderdomaincore.event;

import com.foodordersystem.orderdomaincore.entity.Order;
import com.foodordersystem.orderservice.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;
}
