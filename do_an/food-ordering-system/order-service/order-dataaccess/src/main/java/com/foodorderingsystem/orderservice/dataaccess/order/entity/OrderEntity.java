package com.foodorderingsystem.orderservice.dataaccess.order.entity;

import com.foodorderingsystem.common.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table("orders")
@Setter
@Builder
@AllArgsConstructor
public class OrderEntity {
    @Id
    private UUID id;
    private UUID customerId;
    private UUID restaurantId;
    private UUID trackingId;
    private BigDecimal price;
    private OrderStatus orderStatus;
    private String failureMessages;
    // One to One
    private OrderAddressEntity address;
    // One to Many
    private List<OrderItemEntity> items;

    public void addItem(OrderItemEntity orderItem) {
        this.items.add(orderItem);
    }

    public void removeItem(OrderItemEntity orderItem) {
            this.items.remove(orderItem);
    }
}
