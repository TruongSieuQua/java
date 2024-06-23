package com.tjn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
       WebDriverManager.edgedriver().setup();
       driver = new EdgeDriver();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
