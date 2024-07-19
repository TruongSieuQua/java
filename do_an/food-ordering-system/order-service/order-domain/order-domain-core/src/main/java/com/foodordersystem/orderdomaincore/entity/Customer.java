package com.foodordersystem.orderdomaincore.entity;

import com.foodordersystem.orderservice.domain.entity.AggregateRoot;
import com.foodordersystem.orderservice.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {}
    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
