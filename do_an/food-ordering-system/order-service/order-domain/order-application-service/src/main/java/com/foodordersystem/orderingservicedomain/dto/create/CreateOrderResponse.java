package com.foodordersystem.orderingservicedomain.dto.create;

import com.foodordersystem.orderservice.domain.valueobject.OrderStatus;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @Nonnull
    private final UUID orderTrackingId;
    @Nonnull
    private final OrderStatus orderStatus;
    @Nonnull
    private final String message;
}
