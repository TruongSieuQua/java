package com.tjn.aggregator;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.TestInfo;

import java.lang.reflect.Method;
public class CustomDisplayNameGenerator extends DisplayNameGenerator.Standard {

//    @Override
//    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
//        return String.format("receive %s then return result %s", testData.getData().toString(), testData.getExpectedResult());
//        return super.generateDisplayNameForMethod(testClass, testMethod);
//    }
}
