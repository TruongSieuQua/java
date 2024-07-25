package com.foodorderingsystem.orderservice.dataaccess.order.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItemEntityId implements Serializable {
    @Id
    private Long id;

    @Id
    @Transient
    private OrderEntity order;
}
