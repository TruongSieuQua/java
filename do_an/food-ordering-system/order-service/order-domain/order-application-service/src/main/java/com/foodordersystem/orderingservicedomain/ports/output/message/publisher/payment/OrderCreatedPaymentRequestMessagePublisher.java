package com.foodordersystem.orderingservicedomain.ports.output.message.publisher.payment;

import com.foodordersystem.orderdomaincore.event.OrderCreatedEvent;
import com.foodordersystem.orderservice.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
