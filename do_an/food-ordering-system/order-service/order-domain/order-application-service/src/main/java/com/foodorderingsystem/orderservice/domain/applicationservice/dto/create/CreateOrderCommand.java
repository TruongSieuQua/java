package com.foodorderingsystem.orderservice.domain.applicationservice.dto.create;

import jakarta.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {
   @Nonnull
    private final UUID customerId;
   @Nonnull
   private final UUID restaurantId;
   @Nonnull
   private final BigDecimal price;
   @Nonnull
    private final List<OrderItem> items;
   @Nonnull
   private final OrderAddress address;
}
