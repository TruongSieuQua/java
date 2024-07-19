package com.foodordersystem.orderservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@EqualsAndHashCode
public abstract class BaseEntity<ID> {
    private ID id;
}
