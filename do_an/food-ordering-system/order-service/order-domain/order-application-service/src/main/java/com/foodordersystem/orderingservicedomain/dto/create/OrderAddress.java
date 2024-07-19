package com.foodordersystem.orderingservicedomain.dto.create;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {
    @Nonnull
    @Max(value = 50)
    private final String street;
    @Nonnull
    private final String postalCode;
    @Nonnull
    @Max(value = 50)
    private final String city;

}
