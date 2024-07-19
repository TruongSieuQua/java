package com.foodordersystem.orderingservicedomain;

import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderCommand;
import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderResponse;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderQuery;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderResponse;
import com.foodordersystem.orderingservicedomain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
@Slf4j
@RequiredArgsConstructor
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;

    private final OrderTrackCommandHandler orderTrackCommandHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
