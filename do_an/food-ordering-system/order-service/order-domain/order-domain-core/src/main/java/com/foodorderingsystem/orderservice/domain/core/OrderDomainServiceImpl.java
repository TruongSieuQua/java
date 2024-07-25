package com.foodorderingsystem.orderservice.domain.core;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.orderservice.domain.core.entity.Product;
import com.foodorderingsystem.orderservice.domain.core.entity.Restaurant;
import com.foodorderingsystem.orderservice.domain.core.event.OrderCancelledEvent;
import com.foodorderingsystem.orderservice.domain.core.event.OrderCreatedEvent;
import com.foodorderingsystem.orderservice.domain.core.event.OrderPaidEvent;
import com.foodorderingsystem.orderservice.domain.core.exception.OrderDomainException;
import com.foodorderingsystem.common.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService{
    private static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
           setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated!", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid!", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved!", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order with id: {} is cancelling!!", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is canceled!!", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if(!restaurant.isActive()){
            throw new OrderDomainException(String.format("Restaurant with id %s is currently not active!",
                    restaurant.getId().getValue()));
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        Map<Integer, Product> restaurantProductMap = restaurant.getProducts().stream()
                .collect(Collectors.toMap(
                        Product::hashCode,
                        Function.identity(),
                        (existing, replacement) -> {
                            if(!existing.getPrice().equals(replacement.getPrice())
                                    || !existing.getName().equals(replacement.getName())){
                                throw new DomainException(String.format("Product %s:%s and product %s:%s have same id %s",
                                        existing.getName(), existing.getPrice().getAmount(),
                                        replacement.getName(), replacement.getPrice().getAmount(),
                                        existing.getId().getValue()
                                ));
                            }
                            return existing;
                        }));
        order.getItems().forEach(orderItem -> {
            Product currentProduct = orderItem.getProduct();
            Product restaurantProduct = restaurantProductMap.get(currentProduct.hashCode());
            if(restaurantProduct!=null){
                currentProduct.updateWithConfirmedNameAndPrice(
                        restaurantProduct.getName(),
                        restaurantProduct.getPrice()
                );
            }
        });
    }
}
