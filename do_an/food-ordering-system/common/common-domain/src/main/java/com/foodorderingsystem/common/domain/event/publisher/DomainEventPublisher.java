package com.foodorderingsystem.common.domain.event.publisher;

import com.foodorderingsystem.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent<?>> {
    void publish(T domainEvent);
}
