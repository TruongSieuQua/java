package com.foodordersystem.orderingservicedomain.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/*
 * Client use this command to query latest state of Order
 */
@Getter
@Builder
@AllArgsConstructor
public class TrackOrderQuery {
    @NotNull
    private final UUID orderTrackingId;
}
