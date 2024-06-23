package com.tjn.aggregator;

//public record TestData<T, U>(String id, T data, U expectedResult){}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class TestData<T, U> {
    protected String id;
    protected T data;
    protected U expectedResult;

    // No-argument constructor
    public TestData() {}

    public TestData(String id, T data, U expectedResult){
        this.id = id;
        this.data = data;
        this.expectedResult = expectedResult;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for data
    public T getData() {
        return data;
    }

    // Setter for data
    public void setData(T data) {
        this.data = data;
    }

    // Getter for expectedResult
    public U getExpectedResult() {
        return expectedResult;
    }

    // Setter for expectedResult
    public void setExpectedResult(U expectedResult) {
        this.expectedResult = expectedResult;
    }
}