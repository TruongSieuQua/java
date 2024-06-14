package com.tjn.controller;

import com.tjn.dto.SensorDto;
import com.tjn.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("sensors")
public class SensorController {

    private final SensorService sensorService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<SensorDto>> get(@PathVariable("id") Integer id){
        return sensorService.get(id).map(s -> ResponseEntity.ok().body(s));
    }

    @PostMapping("/{id}")
    public Mono<ResponseEntity<SensorDto>> update(@PathVariable("id") Integer id, @RequestBody SensorDto req) {
        return sensorService.update(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }
}
