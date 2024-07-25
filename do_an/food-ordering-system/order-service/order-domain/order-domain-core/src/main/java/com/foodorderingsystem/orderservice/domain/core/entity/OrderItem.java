package com.foodorderingsystem.orderservice.domain.core.entity;

import com.foodorderingsystem.orderservice.domain.core.valueobject.OrderItemId;
import com.foodorderingsystem.common.domain.entity.BaseEntity;
import com.foodorderingsystem.common.domain.valueobject.Money;
import com.foodorderingsystem.common.domain.valueobject.OrderId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    @Builder
    private OrderItem(OrderItemId orderItemId, Product product, int quantity, Money price, Money subTotal) {
        super.setId(orderItemId);
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid(){
        return this.price.isGreaterThanZero() &&
                this.price.equals(product.getPrice()) &&
                this.price.multiply(quantity).equals(subTotal);
    }
}
