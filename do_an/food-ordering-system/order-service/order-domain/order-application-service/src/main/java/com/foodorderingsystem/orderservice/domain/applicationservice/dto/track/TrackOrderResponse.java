package com.foodorderingsystem.orderservice.domain.applicationservice.dto.track;

import com.foodorderingsystem.common.domain.valueobject.OrderStatus;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
    @Nonnull
    private final UUID orderTrackingId;
    @NonNull
    private final OrderStatus orderStatus;
    private final List<String> failureMessages;
}
