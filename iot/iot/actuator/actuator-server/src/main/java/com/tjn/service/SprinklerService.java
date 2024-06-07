package com.tjn.service;

import com.tjn.config.ServiceUrlConfig;
import com.tjn.dto.ForestResponse;
import com.tjn.dto.SensorResponse;
import com.tjn.dto.SprinklerDto;
import com.tjn.dto.UpdateForestStateDto;
import com.tjn.mapper.SprinklerMapper;
import com.tjn.model.Sprinkler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprinklerService {
    private Map<Integer, Sprinkler> db;

    private static final Logger LOGGER = LoggerFactory.getLogger(SprinklerService.class);

    private final WebClient webClient;

    private final SprinklerJsonProducer sprinklerJsonProducer;

    private final ServiceUrlConfig serviceUrlConfig;

    private final SprinklerMapper sprinklerMapper;

    private final Sinks.Many<SensorResponse> sensorTemperatureSink = Sinks.many().replay().latest();

    private final Sinks.Many<SprinklerDto> sprinklerEventSink = Sinks.many().replay().latest();

    @PostConstruct
    public void init() {
        this.db = Map.of(
                1, new Sprinkler(1, "f1", false, 30d, 100d),
                2, new Sprinkler(2, "f2", false, 30d, 100d),
                3, new Sprinkler(3, "f3", false, 30d, 100d)
        );
        // publish
        autoUpdateSprinklerBaseOnTemperature();
        // consumer
        changeForestState();
        updateSprinkler();

    }

    private Sprinkler findSpringkerByForestName(String forestName) {
        Optional<Sprinkler> sprinkler = db.values().stream()
                .filter(s -> s.getForestName().equals(forestName))
                .findFirst();
        return sprinkler.orElse(null);
    }

    private boolean shouldUpdateSprinkler(SprinklerDto dto, Sprinkler sprinkler){
        return sprinkler.getState() != dto.state();
    }

    @RabbitListener(queues = {"${rabbitmq.queue.sensorTemperature.name}"})
    private void consumeSensorTemperatureMessage(SensorResponse res) {
        sensorTemperatureSink.tryEmitNext(res);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.actuatorState.name}"})
    public void consumeSprinklerMessage(SprinklerDto res){
        System.out.println("consumeSprinklerMessage \n\n\n");
        sprinklerEventSink.tryEmitNext(res);
    }

    private void autoUpdateSprinklerBaseOnTemperature() {
        sensorTemperatureSink
                .asFlux()
                .doOnNext((sr) -> {
                    Sprinkler sprinkler = this.findSpringkerByForestName(sr.forestName());
                    Sprinkler prevSprinkler = sprinkler.makeCopy();
                    if (sr.temperature() > sprinkler.getThreshold()) {
                        sprinkler.setState(true);
                    } else if (sr.temperature() < sprinkler.getCutOffThreshold()) {
                        sprinkler.setState(false);
                    }
                    if(prevSprinkler.getState() != sprinkler.getState()){
                        sprinklerJsonProducer.sendJsonMessage(sprinklerMapper.toSprinklerDto(sprinkler));
                    }
                }).subscribe();
    }

    private void changeForestState() {
        sprinklerEventSink
                .asFlux()
//                .filter(dto -> {
//                    Sprinkler prevSprinkler = this.db.get(dto.id());
//                    return dto.state() != prevSprinkler.getState() ;
//                })
                .doOnNext(s -> {
                    System.out.println("changeForestState \n\n\n");
                    URI url = UriComponentsBuilder
                            .fromHttpUrl(serviceUrlConfig.forest())
                            .path("/forests/{forestName}")
                            .buildAndExpand(s.forestName())
                            .toUri();
                    var updateForestDto = new UpdateForestStateDto(s.state() ? "extinguish" : "normal");

                    webClient.post()
                            .uri(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(updateForestDto)
                            .retrieve()
                            .bodyToMono(ForestResponse.class)
                            .retry(3)
                            .doOnNext(System.out::println)
                            .doOnError(e -> {
                                System.err.println("Error during WebClient call after retries: " + e.getMessage());
                            }).subscribe();
                })
                .subscribe();
    }

    private void updateSprinkler() {
        sprinklerEventSink.asFlux().doOnNext(dto ->{
            System.out.println("updateSprinkler");
            Sprinkler sprinkler = this.db.get(dto.id());
            if(shouldUpdateSprinkler(dto, sprinkler)){
                sprinklerMapper.updateSprinklerFromDto(dto, sprinkler);
            }
        }).subscribe();
    }
}
