package com.foodordersystem.orderservice.domain.event.publisher;

import com.foodordersystem.orderservice.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent<?>> {
    void publish(T domainEvent);
}
