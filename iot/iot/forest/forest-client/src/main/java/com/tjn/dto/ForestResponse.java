package com.tjn.dto;

public record ForestResponse(String name, Double temperature) {
    public ForestResponse {
        // You can add additional validation or logic here if needed.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (temperature == null) {
            throw new IllegalArgumentException("Temperature cannot be null");
        }
    }
}
