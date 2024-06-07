package com.tjn.controller;

import com.tjn.dto.SensorDto;
import com.tjn.dto.SensorResponse;
import com.tjn.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SensorResponse> sprinklerDtoStream(){
        return sensorService.temperatureStream();
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<SensorDto>> updateState(@PathVariable("id") Integer id, @RequestBody SensorDto req) {
        return this.sensorService.updateSensorState(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }

}
