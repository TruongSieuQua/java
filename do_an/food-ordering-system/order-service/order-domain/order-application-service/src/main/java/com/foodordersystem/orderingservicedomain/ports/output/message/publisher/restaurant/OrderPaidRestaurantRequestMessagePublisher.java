package com.foodordersystem.orderingservicedomain.ports.output.message.publisher.restaurant;

import com.foodordersystem.orderdomaincore.event.OrderPaidEvent;
import com.foodordersystem.orderservice.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {}
