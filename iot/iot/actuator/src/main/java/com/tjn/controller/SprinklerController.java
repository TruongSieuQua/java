package com.tjn.controller;

import com.tjn.dto.SprinklerDto;
import com.tjn.model.Sprinkler;
import com.tjn.service.SprinklerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/sprinklers")
@RequiredArgsConstructor
public class SprinklerController {

    private final SprinklerService sprinklerService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Sprinkler> sprinklersStateStream() {
        return sprinklerService.sprinklerStateStream();
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<SprinklerDto>> updateSprinkler(@PathVariable("id") Integer id, @RequestBody SprinklerDto req){
        return sprinklerService.updateSprinkler(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }
}
