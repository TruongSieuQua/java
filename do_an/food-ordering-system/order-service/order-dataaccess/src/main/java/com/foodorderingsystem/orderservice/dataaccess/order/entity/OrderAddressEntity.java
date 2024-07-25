package com.foodorderingsystem.orderservice.dataaccess.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("order_address")
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderAddressEntity {
    @Id
    private UUID id;

    private String street;
    private String postalCode;
    private String city;
}
