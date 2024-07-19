package com.foodordersystem.orderingservicedomain.dto.create;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    @Nonnull
    private final UUID productId;
    @NonNull
    private final Integer quantity;
    @NonNull
    private BigDecimal price;
    @NonNull
    private BigDecimal subTotal;
}
