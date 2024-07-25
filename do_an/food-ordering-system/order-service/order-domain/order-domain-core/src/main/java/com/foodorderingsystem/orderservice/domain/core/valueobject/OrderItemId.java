package com.foodorderingsystem.orderservice.domain.core.valueobject;

import com.foodorderingsystem.common.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
