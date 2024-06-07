package com.tjn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tjn.service")
public record ServiceUrlConfig(String forest) {
}
