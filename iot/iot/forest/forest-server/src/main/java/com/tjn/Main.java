package com.tjn;

import com.tjn.config.ServiceUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableConfigurationProperties(ServiceUrlConfig.class)
@EnableWebFlux
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}