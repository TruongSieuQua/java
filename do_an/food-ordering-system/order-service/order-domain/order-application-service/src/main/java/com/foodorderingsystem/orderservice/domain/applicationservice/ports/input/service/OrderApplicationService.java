package com.foodorderingsystem.orderservice.domain.applicationservice.ports.input.service;

import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderCommand;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderResponse;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.track.TrackOrderQuery;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid  CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid  TrackOrderQuery trackOrderQuery);
}
