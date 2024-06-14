package com.tjn.controller;

import com.tjn.dto.SprinklerDto;
import com.tjn.service.SprinklerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sprinklers")
@RequiredArgsConstructor
public class SprinklerController {

    private final SprinklerService sprinklerService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<SprinklerDto>> getSprinkler(@PathVariable("id") Integer id){
        return sprinklerService.getSprinkler(id)
                .map(s -> ResponseEntity.ok().body(s));
    };

    @GetMapping("/all")
    public Mono<ResponseEntity<List<SprinklerDto>>> getAllSprinkers(){
        return sprinklerService.getAllSprinklers().map(
                sl -> ResponseEntity.ok().body(sl)
        );
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<SprinklerDto>> update(@PathVariable("id") Integer id, @RequestBody SprinklerDto req){
        return sprinklerService.updateSprinkler(id, req)
                .map(s -> ResponseEntity.ok().body(s));
    }
}
