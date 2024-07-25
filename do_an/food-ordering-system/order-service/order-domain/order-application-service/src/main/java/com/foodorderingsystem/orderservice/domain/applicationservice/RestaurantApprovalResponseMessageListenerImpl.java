package com.foodorderingsystem.orderservice.domain.applicationservice;

import com.foodorderingsystem.orderservice.domain.applicationservice.dto.message.RestaurantApprovalResponse;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.input.message.listener.restaurant.RestaurantApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {
    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
