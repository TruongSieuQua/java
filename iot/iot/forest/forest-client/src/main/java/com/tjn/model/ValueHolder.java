package com.tjn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueHolder<T> {
    private T previousValue;
    private T value;
}
