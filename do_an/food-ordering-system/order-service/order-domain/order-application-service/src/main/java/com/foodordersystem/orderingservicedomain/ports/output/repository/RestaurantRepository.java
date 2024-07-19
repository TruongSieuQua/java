package com.foodordersystem.orderingservicedomain.ports.output.repository;

import com.foodordersystem.orderdomaincore.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
