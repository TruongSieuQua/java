package com.tjn.aggregator;

//public record TestData<T, U>(String id, T data, U expectedResult){}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase<T, U> {
    protected String id;
    protected String name;
    protected T data;
    protected U expectedResult;
}