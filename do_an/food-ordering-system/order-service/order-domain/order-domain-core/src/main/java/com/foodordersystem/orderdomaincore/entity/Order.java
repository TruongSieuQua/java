package com.foodordersystem.orderdomaincore.entity;

import com.foodordersystem.orderdomaincore.exception.OrderDomainException;
import com.foodordersystem.orderdomaincore.valueobject.OrderItemId;
import com.foodordersystem.orderdomaincore.valueobject.StreetAddress;
import com.foodordersystem.orderdomaincore.valueobject.TrackingId;
import com.foodordersystem.orderservice.domain.entity.AggregateRoot;
import com.foodordersystem.orderservice.domain.valueobject.*;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessage;

    @Builder
    private Order(OrderId orderId, CustomerId customerId, RestaurantId restaurantId, StreetAddress deliveryAddress, Money price,
        List<OrderItem> items, TrackingId trackingId, OrderStatus orderStatus, List<String> failureMessage) {
        super.setId(orderId);
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.deliveryAddress = deliveryAddress;
        this.price = price;
        this.items = items;
        this.trackingId = trackingId;
        this.orderStatus = orderStatus;
        this.failureMessage = failureMessage;
    }

    public void initializeOrder(){
        Order order = Order.builder()
                .customerId(new CustomerId(UUID.randomUUID()))
                .build();

        setId(new OrderId(UUID.randomUUID()));
        this.trackingId = new TrackingId(UUID.randomUUID());
        this.orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for(OrderItem orderItem : this.items){
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    public void validateOrder(){
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void pay(){
        if(this.orderStatus != OrderStatus.PENDING){
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        this.orderStatus = OrderStatus.PAID;
    }

    public void approve(){
        if(this.orderStatus != OrderStatus.PAID){
            throw new OrderDomainException("Order is not in correct state for approve operation");
        }
        this.orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessage){
        if(this.orderStatus != OrderStatus.PAID){
            throw new OrderDomainException("Order is not in correct state for init cancel operation");
        }
        this.orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessage);
    }

    public void cancel(List<String> failureMessage){
        if(this.orderStatus == OrderStatus.CANCELLING || this.orderStatus == OrderStatus.PENDING){
            throw new OrderDomainException("Order is not in correct state for cancel operation");
        }
        this.orderStatus = OrderStatus.CANCELED;
    }

    private void validateInitialOrder() {
        if(this.orderStatus != null || getId() != null){
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    private void validateTotalPrice() {
        if(this.price == null || !this.price.isGreaterThanZero()){
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotal = this.items.stream().map(orderItem -> {
           validateItemPrice(orderItem);
           return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if(!this.price.equals(orderItemsTotal)){
            throw new OrderDomainException(String.format("Total price: %s is not equal to Order items total: %s!",
                    this.price.getAmount(), orderItemsTotal.getAmount()));
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if(!orderItem.isPriceValid()){
            throw new OrderDomainException(String.format("Order item price %s is not valid for product %s!",
                    orderItem.getPrice().getAmount(), orderItem.getProduct().getId().getValue()));
        }
    }

    private void updateFailureMessages(List<String> failureMessage) {
        if(this.failureMessage != null && failureMessage !=null){
            this.failureMessage.addAll(failureMessage.stream().filter(message -> !message.isBlank()).toList());
        }
        if(this.failureMessage == null){
            this.failureMessage = failureMessage;
        }
    }
}
