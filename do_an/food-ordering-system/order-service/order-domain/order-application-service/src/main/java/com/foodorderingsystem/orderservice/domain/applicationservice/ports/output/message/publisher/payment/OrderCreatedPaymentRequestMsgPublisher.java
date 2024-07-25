package com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.payment;

import com.foodorderingsystem.orderservice.domain.core.event.OrderCreatedEvent;
import com.foodorderingsystem.common.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMsgPublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
