package com.foodorderingsystem.orderservice.domain.core.valueobject;

import com.foodorderingsystem.common.domain.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
