package com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.restaurant;

import com.foodorderingsystem.orderservice.domain.core.event.OrderPaidEvent;
import com.foodorderingsystem.common.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMsgPublisher extends DomainEventPublisher<OrderPaidEvent> {}
