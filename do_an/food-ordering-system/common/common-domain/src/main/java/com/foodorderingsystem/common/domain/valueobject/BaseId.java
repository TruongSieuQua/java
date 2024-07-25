package com.foodorderingsystem.common.domain.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public abstract class BaseId<T> {
    private final T value;
}
