package com.tjn.controller;

import com.tjn.dto.SensorDto;
import com.tjn.dto.SensorResponse;
import com.tjn.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Flux<SensorResponse> sensorStream(){
        return sensorService.temperatureStream();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<SensorDto>> getSensor(@PathVariable("id") Integer id){
        return this.sensorService.getSensor(id).map(s -> ResponseEntity.ok().body(s));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<SensorDto>> updateState(@PathVariable("id") Integer id, @RequestBody SensorDto req) {
        return this.sensorService.updateSensor(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }

}
