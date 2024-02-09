package com.example.selenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class WebDriverWaitConfig {

    @Value("${default.timeout:30}")
    private int timeout;

    @Bean
    public WebDriverWait webDriverWait(WebDriver webDriver){
        return new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
    }
}
