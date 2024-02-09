package com.example.selenium;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

@SpringBootTest
public class SpringBaseTest extends AbstractTestNGSpringContextTests {



    @BeforeClass
    public void setUp() {

    }
}
