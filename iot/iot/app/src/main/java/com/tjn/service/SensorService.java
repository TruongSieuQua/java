package com.tjn.service;

import com.tjn.config.ServiceUrlConfig;
import com.tjn.dto.SensorDto;
import com.tjn.dto.SensorResponse;
import com.tjn.dto.UpdateForestStateDto;
import com.tjn.model.Forest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final Sinks.Many<SensorResponse> sensorTemperatureSink = Sinks.many().replay().latest();

    private final ServiceUrlConfig serviceUrlConfig;

    private final WebClient webClient;

    @RabbitListener(queues = {"${rabbitmq.queue.sensorTemperature.name}"})
    private void consumeSensorTemperatureMessage(SensorResponse res) {
        sensorTemperatureSink.tryEmitNext(res);
    }

    public Flux<SensorResponse> temperatureStream(){
        return this.sensorTemperatureSink.asFlux();
    }

    public Mono<SensorDto> updateSensorState(Integer id, SensorDto req){
        URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrlConfig.forest())
                .path("/sensors/{id}")
                .buildAndExpand(id)
                .toUri();

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .retrieve()
                .bodyToMono(SensorDto.class)
                .retry(3)
                .doOnNext(System.out::println)
                .doOnError(e -> {
                    System.err.println("Error during WebClient call after retries: " + e.getMessage());
                });
    }
}
