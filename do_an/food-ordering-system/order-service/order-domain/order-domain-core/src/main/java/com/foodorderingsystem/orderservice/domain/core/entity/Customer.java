package com.foodorderingsystem.orderservice.domain.core.entity;

import  com.foodorderingsystem.common.domain.entity.AggregateRoot;
import  com.foodorderingsystem.common.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {}
    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
