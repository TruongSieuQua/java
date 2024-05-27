package com.tjn.controller;

import com.tjn.dto.ForestResponse;
import com.tjn.service.ForestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class ForestController {
    private final ForestService forestService;

    @GetMapping("/{forestName}/temperature-stream")
    public Flux<ForestResponse> getTemperatureStream(@PathVariable String forestName) {
        return forestService.getTemperatureStream(forestName);
    }

    @PostMapping("/{forestName}/change-state")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> changeState(@PathVariable String forestName, @RequestParam String newState) {
        return forestService.changeState(forestName, newState);
    }
}
