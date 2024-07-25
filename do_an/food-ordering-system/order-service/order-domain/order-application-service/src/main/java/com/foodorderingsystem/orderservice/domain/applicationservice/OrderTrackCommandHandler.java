package com.foodorderingsystem.orderservice.domain.applicationservice;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.orderservice.domain.core.exception.OrderNotFoundException;
import com.foodorderingsystem.orderservice.domain.core.valueobject.TrackingId;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.track.TrackOrderQuery;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.track.TrackOrderResponse;
import com.foodorderingsystem.orderservice.domain.applicationservice.mapper.OrderDataMapper;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> optionalOrder =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (optionalOrder.isEmpty()) {
            log.info("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id: " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(optionalOrder.get());
    }
}
