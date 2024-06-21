package com.tjn.controller;

import com.tjn.dto.ForestDto;
import com.tjn.dto.ForestResponse;
import com.tjn.dto.UpdateForestStateDto;
import com.tjn.service.ForestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("forests")
public class ForestController {
    private final ForestService forestService;

    @GetMapping(value = "/stream/{forestName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ForestResponse> getTemperatureStream(@PathVariable("forestName") String forestName) {
        return forestService.getTemperatureStream(forestName);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ForestDto> forestStream(){
        return forestService.forestStream();
    }

    @GetMapping(value = "")
    public Mono<ResponseEntity<List<ForestDto>>> getAllForest(){
        return forestService.getAllForest()
                .map(fl -> ResponseEntity.ok().body(fl));
    }

    @PostMapping("/{forestName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<ForestDto>> changeState(@PathVariable("forestName") String forestName, @RequestBody UpdateForestStateDto req) {
        return forestService.changeState(forestName, req)
                .map(forest -> ResponseEntity.status(HttpStatus.OK).body(forest));
    }
}
