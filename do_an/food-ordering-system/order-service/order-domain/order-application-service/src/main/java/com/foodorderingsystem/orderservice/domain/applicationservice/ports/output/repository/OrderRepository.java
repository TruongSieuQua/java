package com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.orderservice.domain.core.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
