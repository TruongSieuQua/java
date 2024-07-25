package com.foodorderingsystem.orderservice.domain.core.entity;

import com.foodorderingsystem.common.domain.entity.AggregateRoot;
import  com.foodorderingsystem.common.domain.valueobject.RestaurantId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class Restaurant extends AggregateRoot<RestaurantId> {
    @Getter
    private final List<Product> products;
    private boolean active;

    @Builder
    private Restaurant(RestaurantId restaurantId, List<Product> products, boolean active) {
        super.setId(restaurantId);
        this.products = products;
        this.active = active;
    }
    public boolean isActive(){
        return this.active;
    }
}
