package com.foodordersystem.orderingservicedomain.ports.output.repository;

import com.foodordersystem.orderdomaincore.entity.Order;
import com.foodordersystem.orderdomaincore.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
