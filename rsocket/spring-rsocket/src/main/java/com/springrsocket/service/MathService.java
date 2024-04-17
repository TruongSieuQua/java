package com.springrsocket.service;

import com.springrsocket.dto.ChartResponseDto;
import com.springrsocket.dto.ComputationRequestDto;
import com.springrsocket.dto.ComputationResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class MathService {

    public Mono<Void> print(Mono<ComputationRequestDto> requestDtoMono){
        return requestDtoMono
                .doOnNext(System.out::println)
                .then();
    }

    public Mono<ComputationResponseDto> findSquare(Mono<ComputationRequestDto> dto){
        return dto.map(ComputationRequestDto::getInput)
                .map(i -> new ComputationResponseDto(i, i*i));
    }

//    public Flux<ComputationResponseDto> tableStream(ComputationRequestDto dto){
//        return Flux.range(1, 10)
//                .map(i -> new ComputationResponseDto(dto.getInput(), i* dto.getInput()));
//    }

    // For Test 09
    public Flux<ComputationResponseDto> tableStream(ComputationRequestDto dto){
        return Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new ComputationResponseDto(dto.getInput(), i* dto.getInput()));
    }

    public Flux<ChartResponseDto> chartStream(Flux<ComputationRequestDto> dto){
        System.out.println("chartStream method expect this print one time");
        return dto.map(ComputationRequestDto::getInput)
                .map(i -> new ChartResponseDto(i, i*i+1));
    }
}
