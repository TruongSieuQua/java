package com.foodordersystem.orderdomaincore.valueobject;

import com.foodordersystem.orderservice.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
