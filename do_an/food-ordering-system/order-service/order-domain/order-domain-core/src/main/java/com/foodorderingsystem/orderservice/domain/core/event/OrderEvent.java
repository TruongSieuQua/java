package com.foodorderingsystem.orderservice.domain.core.event;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.common.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;
}
