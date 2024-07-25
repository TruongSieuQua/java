package com.foodorderingsystem.orderservice.domain.applicationservice;

import com.foodorderingsystem.orderservice.domain.core.event.OrderCreatedEvent;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderCommand;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderResponse;
import com.foodorderingsystem.orderservice.domain.applicationservice.mapper.OrderDataMapper;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMsgPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreateCommandHandler {
    private final OrderCreatedHelper orderCreatedHelper;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMsgPublisher orderCreatedPaymentRequestMessagePublisher;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreatedHelper.persistOrder(createOrderCommand);
        log.info("Persistent order successfully with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        log.info("Publish orderCreatedEvent successfully with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(), "Order created successfully!");
    }


}
