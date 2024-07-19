package com.foodordersystem.orderingservicedomain.mapper;

import com.foodordersystem.orderdomaincore.entity.Order;
import com.foodordersystem.orderdomaincore.entity.OrderItem;
import com.foodordersystem.orderdomaincore.entity.Product;
import com.foodordersystem.orderdomaincore.entity.Restaurant;
import com.foodordersystem.orderdomaincore.valueobject.StreetAddress;
import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderCommand;
import com.foodordersystem.orderingservicedomain.dto.create.CreateOrderResponse;
import com.foodordersystem.orderingservicedomain.dto.create.OrderAddress;
import com.foodordersystem.orderingservicedomain.dto.track.TrackOrderResponse;
import com.foodordersystem.orderservice.domain.valueobject.CustomerId;
import com.foodordersystem.orderservice.domain.valueobject.Money;
import com.foodordersystem.orderservice.domain.valueobject.ProductId;
import com.foodordersystem.orderservice.domain.valueobject.RestaurantId;
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
            List<com.foodordersystem.orderingservicedomain.dto.create.OrderItem> orderItems) {
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
