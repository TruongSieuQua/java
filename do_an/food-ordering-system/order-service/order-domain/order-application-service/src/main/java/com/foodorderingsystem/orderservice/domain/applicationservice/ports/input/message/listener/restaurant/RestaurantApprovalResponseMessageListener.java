package com.foodorderingsystem.orderservice.domain.applicationservice.ports.input.message.listener.restaurant;

import com.foodorderingsystem.orderservice.domain.applicationservice.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
