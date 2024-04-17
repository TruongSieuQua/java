package com.springrsocket.controller;

import com.springrsocket.dto.ChartResponseDto;
import com.springrsocket.dto.ComputationRequestDto;
import com.springrsocket.dto.ComputationResponseDto;
import com.springrsocket.service.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class MathController {

    private final MathService service;

    // Dua vao input output => fireAndForget
    @MessageMapping("math.service.print")
    public Mono<Void> print(Mono<ComputationRequestDto> request){
        return this.service.print(request);
    }

    // Dua vao input output => Request-Response
    @MessageMapping("math.service.square")
    public Mono<ComputationResponseDto> findSquare(Mono<ComputationRequestDto> requestDtoMono){
        return this.service.findSquare(requestDtoMono);
    }

    // Dua vao input output => Request Stream
    @MessageMapping("math.service.table")
    public Flux<ComputationResponseDto> tableStream(Mono<ComputationRequestDto> requestDtoMono){
        // request mono output mono --> flatMap
        // request mono output flux --> flapMapMany
        return requestDtoMono.flatMapMany(this.service::tableStream);
    }

    // Dua vao input output => Request Channel
    @MessageMapping("math.service.chart")
    public Flux<ChartResponseDto> chartStream(Flux<ComputationRequestDto> requestDtoFlux){
        return this.service.chartStream(requestDtoFlux);
    }
}
