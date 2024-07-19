package com.foodordersystem.orderingservicedomain.ports.input.message.listener.restaurant;

import com.foodordersystem.orderingservicedomain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
