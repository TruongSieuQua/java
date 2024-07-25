package com.foodorderingsystem.orderservice.dataaccess.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("order_items")
@Setter
@Builder
@AllArgsConstructor
public class OrderItemEntity {
    @Id
    private Long id;

    private UUID productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;
}
