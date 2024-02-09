package com.example.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory {

    public WebDriver getDriver(String browser){
        return switch (browser) {
            case "edge" -> new EdgeDriver();
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> throw new RuntimeException("Broser driver not found!");
        };
    }
}
