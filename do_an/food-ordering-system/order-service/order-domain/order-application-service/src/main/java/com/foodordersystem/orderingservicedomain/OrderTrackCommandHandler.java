package com.foodordersystem.orderingservicedomain;

import com.foodordersystem.orderdomaincore.entity.Order;
import com.foodordersystem.orderdomaincore.exception.OrderNotFoundException;
import com.foodordersystem.orderdomaincore.valueobject.TrackingId;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderQuery;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderResponse;
import com.foodordersystem.orderingservicedomain.mapper.OrderDataMapper;
import com.foodordersystem.orderingservicedomain.ports.output.repository.OrderRepository;
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
