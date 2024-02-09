package com.example.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

@Lazy
@Configuration
@Profile("!remote")
public class WebDriverConfig {

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver fireFox(){
        WebDriverManager.chromedriver().setup();
        System.out.println("ChromeDriver");
        return new ChromeDriver();
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        firefoxOptions.setCapability("proxy", proxy);
        return new FirefoxDriver(firefoxOptions);
    }

    @Bean
    @ConditionalOnMissingBean //Should keep last
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public WebDriver edgeDriver(){
        WebDriverManager.edgedriver().setup();
        System.out.println("EdgeDriver");
        return new EdgeDriver();
    }


}
