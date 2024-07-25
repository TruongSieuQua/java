package com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.payment;

import com.foodorderingsystem.orderservice.domain.core.event.OrderCancelledEvent;
import com.foodorderingsystem.common.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMsgPublisher extends DomainEventPublisher<OrderCancelledEvent> {}
