package com.tjn.dto;

import lombok.NonNull;

public record SensorDto(Integer id,@NonNull Boolean state, @NonNull String forestName) {}
