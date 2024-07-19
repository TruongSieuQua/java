package com.foodordersystem.orderingservicedomain.ports.input.service;

import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderCommand;
import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderResponse;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderQuery;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid  CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid  TrackOrderQuery trackOrderQuery);
}
