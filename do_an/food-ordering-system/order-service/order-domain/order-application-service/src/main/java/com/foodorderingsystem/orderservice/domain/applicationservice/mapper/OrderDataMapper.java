package com.foodorderingsystem.orderservice.domain.applicationservice.mapper;

import com.foodorderingsystem.orderservice.domain.core.entity.Order;
import com.foodorderingsystem.orderservice.domain.core.entity.OrderItem;
import com.foodorderingsystem.orderservice.domain.core.entity.Product;
import com.foodorderingsystem.orderservice.domain.core.entity.Restaurant;
import com.foodorderingsystem.orderservice.domain.core.valueobject.StreetAddress;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderCommand;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.CreateOrderResponse;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.OrderAddress;
import com.foodorderingsystem.orderservice.domain.applicationservice.dto.track.TrackOrderResponse;
import com.foodorderingsystem.common.domain.valueobject.CustomerId;
import com.foodorderingsystem.common.domain.valueobject.Money;
import com.foodorderingsystem.common.domain.valueobject.ProductId;
import com.foodorderingsystem.common.domain.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                        new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessage())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<com.foodorderingsystem.orderservice.domain.applicationservice.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}
