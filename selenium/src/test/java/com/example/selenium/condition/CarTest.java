package com.example.selenium.condition;

import com.example.selenium.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class CarTest extends SpringBaseTest {

    @Autowired
    private Car car;

    @Test
    public void carTest(){
        this.car.run();
    }
}
