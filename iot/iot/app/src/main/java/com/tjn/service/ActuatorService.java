package com.tjn.service;

import com.tjn.config.ServiceUrlConfig;
import com.tjn.dto.SprinklerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActuatorService {

    private final Sinks.Many<SprinklerDto> actuatorStateSink = Sinks.many().replay().latest();

    private final SprinklerJsonProducer sprinklerProducer;

    private final ServiceUrlConfig serviceUrlConfig;

    private final WebClient webClient;

    @RabbitListener(queues = {"${rabbitmq.queue.actuatorState.name}"})
    private  void consumeSprinklerMessage(SprinklerDto res){
        actuatorStateSink.tryEmitNext(res);
    }

    public Flux<SprinklerDto> sprinklerStream(){
        return actuatorStateSink.asFlux().flatMap(dto -> getSprinkler(dto.id()));
    }

    public Mono<List<SprinklerDto>> getAllSprinkers(){
        URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrlConfig.actuator())
                .path("/sprinklers/all")
                .build()
                .toUri();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<SprinklerDto>>() {})
                .retry(3)
                .doOnError(e -> {
                    System.err.println("Error during WebClient call after retries: " + e.getMessage());
                });
    }

    public Mono<SprinklerDto> getSprinkler(Integer id){
        URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrlConfig.actuator())
                .path("/sprinklers/{id}")
                .buildAndExpand(id)
                .toUri();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(SprinklerDto.class)
                .retry(3)
                .doOnError(e -> {
                    System.err.println("Error during WebClient call after retries: " + e.getMessage());
                });
    }

    public Mono<SprinklerDto> updateSprinkler(Integer id, SprinklerDto dto){
        URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrlConfig.actuator())
                .path("/sprinklers/{id}")
                .buildAndExpand(id)
                .toUri();

        return webClient.patch()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(SprinklerDto.class)
                .retry(3)
                .doOnError(e -> {
                    System.err.println("Error during WebClient call after retries: " + e.getMessage());
                });

    }
}
