package com.tjn.controller;

import com.tjn.dto.SprinklerDto;
import com.tjn.service.ActuatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("sprinklers")
@RequiredArgsConstructor
public class ActuatorController {

    private final ActuatorService actuatorService;

    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Flux<SprinklerDto> sprinklerDtoStream(){
        return actuatorService.sprinklerStream();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<List<SprinklerDto>>> getAllSprinkers(){
        return actuatorService.getAllSprinkers().map(
                sl -> ResponseEntity.ok().body(sl)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<SprinklerDto>> getSpinkler(@PathVariable("id") Integer id){
        return actuatorService.getSprinkler(id)
                .map(s -> ResponseEntity.ok().body(s));
    }

    @PatchMapping("/{id}")

    public Mono<ResponseEntity<SprinklerDto>> updateSprinkler(@PathVariable("id") Integer id, @RequestBody SprinklerDto req){
        return actuatorService.updateSprinkler(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }
}
