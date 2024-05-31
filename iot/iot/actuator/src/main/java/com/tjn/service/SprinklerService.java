package com.tjn.service;

import com.tjn.config.ServiceUrlConfig;
import com.tjn.dto.SensorResponse;
import com.tjn.dto.SprinklerDto;
import com.tjn.dto.UpdateForestStateDto;
import com.tjn.mapper.SprinklerMapper;
import com.tjn.model.Forest;
import com.tjn.model.Sprinkler;
import com.tjn.model.ValueHolder;
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

    private final ServiceUrlConfig serviceUrlConfig;

    private final SprinklerMapper sprinklerMapper;

    private final Sinks.Many<SensorResponse> sensorTemperatureSink = Sinks.many().replay().latest();

    private final Sinks.Many<ValueHolder<Sprinkler>> sprinklerEventSink = Sinks.many().replay().latest();

    @PostConstruct
    public void init() {
        this.db = Map.of(
                1, new Sprinkler(1, "f1", false, 30d, 100d),
                2, new Sprinkler(2, "f2", false, 30d, 100d),
                3, new Sprinkler(3, "f3", false, 30d, 100d)
        );
        autoUpdateSprinklerState();
        sprinklerStateChangeHandler();
    }

    private Sprinkler findSpringkerByForestName(String forestName) {
        Optional<Sprinkler> sprinkler = db.values().stream()
                .filter(s -> s.getForestName().equals(forestName))
                .findFirst();
        return sprinkler.orElse(null);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.sensorTemperature.name}"})
    public void consumeSensorTemperatureMessage(SensorResponse res) {
        System.out.println(res);
        sensorTemperatureSink.tryEmitNext(res);
    }

    private void autoUpdateSprinklerState() {
        sensorTemperatureSink.asFlux().doOnNext((sr) -> {
            Sprinkler sprinkler = this.findSpringkerByForestName(sr.forestName());
            if (sr.temperature() > sprinkler.getThreshold()) {
                Sprinkler prevSprinkler = sprinkler.makeCopy();
                sprinkler.setState(true);
                sprinklerEventSink.tryEmitNext(new ValueHolder<>(prevSprinkler, sprinkler));
            } else if (sr.temperature() < sprinkler.getCutOffThreshold()) {
                Sprinkler prevSprinkler = sprinkler.makeCopy();
                sprinkler.setState(false);
                sprinklerEventSink.tryEmitNext(new ValueHolder<>(prevSprinkler, sprinkler));
            }
        }).subscribe();
    }

    private void sprinklerStateChangeHandler() {
        sprinklerEventSink.asFlux()
                .filter(ses -> ses.getPreviousValue().getState() != ses.getValue().getState())
                .map(ValueHolder::getValue)
                .doOnNext(s -> {
                    URI url = UriComponentsBuilder
                            .fromHttpUrl(serviceUrlConfig.forest())
                            .path("/forests/{forestName}")
                            .buildAndExpand(s.getForestName())
                            .toUri();
                    var updateForestDto = new UpdateForestStateDto(s.getState() ? "extinguish" : "normal");

                    webClient.post()
                            .uri(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(updateForestDto)
                            .retrieve()
                            .bodyToMono(Forest.class)
                            .retry(3)
                            .doOnNext(System.out::println)
                            .doOnError(e -> {
                                System.err.println("Error during WebClient call after retries: " + e.getMessage());
                            })
                            .subscribe();
                }).subscribe();
    }

    public Flux<Sprinkler> sprinklerStateStream() {
        return sprinklerEventSink.asFlux()
                .filter(ses -> ses.getPreviousValue().getState() != ses.getValue().getState())
                .map(ValueHolder::getValue);
    }

    public Mono<SprinklerDto> updateSprinkler(Integer id, SprinklerDto req) {
        return Mono.fromSupplier(() -> this.db.get(id))
                .flatMap(s -> {
                    if (s == null) {
                        return Mono.error(new Exception(String.format("Id %d is not found!", id)));
                    }
                    return Mono.just(s);
                })
                .doOnNext(s -> {
                    sprinklerMapper.updateUserFromDto(req, s);
                })
                .map(sprinklerMapper::toSprinklerDto);
    }
}
