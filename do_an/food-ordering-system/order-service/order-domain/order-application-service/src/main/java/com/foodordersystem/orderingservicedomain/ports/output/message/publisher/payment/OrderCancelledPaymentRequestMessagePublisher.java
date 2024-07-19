package com.foodordersystem.orderingservicedomain.ports.output.message.publisher.payment;

import com.foodordersystem.orderdomaincore.event.OrderCancelledEvent;
import com.foodordersystem.orderservice.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {}
