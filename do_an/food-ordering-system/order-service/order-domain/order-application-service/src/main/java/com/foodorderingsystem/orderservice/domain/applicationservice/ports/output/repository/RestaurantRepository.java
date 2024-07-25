package com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository;

import com.foodorderingsystem.orderservice.domain.core.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
