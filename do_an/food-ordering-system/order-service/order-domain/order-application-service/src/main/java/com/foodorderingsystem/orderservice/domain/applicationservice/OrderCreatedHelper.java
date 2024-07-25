package com.foodorderingsystem.orderservice.domain.applicationservice;

import com.foodorderingsystem.orderservice.domain.core.OrderDomainService;
import com.foodorderingsystem.orderservice.domain.core.entity.Customer;
import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.orderservice.domain.core.entity.Restaurant;
import com.foodorderingsystem.orderservice.domain.core.event.OrderCreatedEvent;
import com.foodorderingsystem.orderservice.domain.core.exception.OrderDomainException;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderCommand;
import com.foodorderingsystem.orderservice.domain.applicationservice.mapper.OrderDataMapper;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.CustomerRepository;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.OrderRepository;
import com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if(customer.isEmpty()){
            log.warn("Cound not find customer with id {}", customerId);
            throw new OrderDomainException("Cound not find customer with id: " + customerId);
        }
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if(optionalRestaurant.isEmpty()){
            log.warn("Cound not find restaurant with id {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Cound not find restaurant with id: " + restaurant.getId());
        }
        return optionalRestaurant.get();
    }

    private Order saveOrder(Order order){
        Order orderResult = orderRepository.save(order);
        if(orderResult == null){
            log.error("Could not save order!");
            throw  new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}!", orderResult.getId().getValue());
        return orderResult;
    }
}
