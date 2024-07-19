package com.foodordersystem.orderingservicedomain;

import com.foodordersystem.orderdomaincore.event.OrderCreatedEvent;
import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderCommand;
import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderResponse;
import com.foodordersystem.orderingservicedomain.mapper.OrderDataMapper;
import com.foodordersystem.orderingservicedomain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreateCommandHandler {
    private final OrderCreatedHelper orderCreatedHelper;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreatedHelper.persistOrder(createOrderCommand);
        log.info("Persistent order successfully with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        log.info("Publish orderCreatedEvent successfully with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(), "Order created successfully!");
    }


}
