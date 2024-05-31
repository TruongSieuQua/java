package com.tjn.controller;

import com.tjn.dto.ForestResponse;
import com.tjn.dto.UpdateForestStateDto;
import com.tjn.model.Forest;
import com.tjn.service.ForestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("forests")
public class ForestController {
    private final ForestService forestService;

    @GetMapping(value = "/stream/{forestName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ForestResponse> getTemperatureStream(@PathVariable("forestName") String forestName) {
        return forestService.getTemperatureStream(forestName);
    }

    @PostMapping("/{forestName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Forest>> changeState(@PathVariable("forestName") String forestName, @RequestBody UpdateForestStateDto req) {
        return forestService.changeState(forestName, req)
                .map(forest -> ResponseEntity.status(HttpStatus.OK).body(forest));
    }
}
