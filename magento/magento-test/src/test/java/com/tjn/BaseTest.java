package com.tjn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
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

    @BeforeAll
    public static void setUpClass() {
        String edgeDriverPath = "E:\\edge\\msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
//        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    public void setUp() {
       driver = new EdgeDriver();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
