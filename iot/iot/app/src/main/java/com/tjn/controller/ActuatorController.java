package com.tjn.controller;

import com.tjn.dto.SprinklerDto;
import com.tjn.service.ActuatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("sprinklers")
@RequiredArgsConstructor
public class ActuatorController {

    private final ActuatorService actuatorService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SprinklerDto> sprinklerDtoStream(){
        return actuatorService.sprinklerStream();
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<SprinklerDto>> updateSprinkler(@PathVariable("id") Integer id, @RequestBody SprinklerDto req){
        return actuatorService.updateSprinkler(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }

}
