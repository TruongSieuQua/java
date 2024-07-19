package com.foodordersystem.orderdomaincore.valueobject;

import com.foodordersystem.orderdomaincore.entity.Order;
import com.foodordersystem.orderservice.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
