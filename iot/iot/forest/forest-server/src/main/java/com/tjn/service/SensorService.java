package com.tjn.service;

import com.tjn.config.ServiceUrlConfig;
import com.tjn.dto.ForestResponse;
import com.tjn.dto.SensorDto;
import com.tjn.dto.SensorResponse;
import com.tjn.mapper.SensorMapper;
import com.tjn.model.Sensor;
import com.tjn.model.ValueHolder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SensorService {

    private Map<Integer, Sensor> db;

    private final SensorMapper sensorMapper;

    private final WebClient webClient;

    private final ServiceUrlConfig serviceUrlConfig;

    private final TemperatureSensorJsonProducer sensorProducer;

    private final Sinks.Many<ValueHolder<Sensor>> sensorEventSink = Sinks.many().replay().latest();

    @PostConstruct
    public void init() {
        Sensor s1 = new Sensor(1, false, "f1");
        Sensor s2 = new Sensor(2, false, "f2");
        Sensor s3 = new Sensor(3, false, "f3");

        this.db = Map.of(
                s1.getId(), s1,
                s2.getId(), s2,
                s3.getId(), s3
        );
        sensorDataToSink();
        startDataStream();
    }

    private void sensorDataToSink() {
        Flux.fromIterable(db.values())
                .filter(Sensor::getState)
                //... neu khoi tao co cai true
                .subscribe();
    }

    private void startDataStream() {
        sensorEventSink.asFlux()
                .filter((ses) ->
                        ses.getPreviousValue().getState() != ses.getValue().getState() && ses.getValue().getState())
                .map(ValueHolder::getValue)
                .flatMap(sensor -> {
                    final URI url = UriComponentsBuilder
                            .fromHttpUrl(serviceUrlConfig.forest())
                            .path("/forests/stream/{forestName}")
                            .buildAndExpand(sensor.getForestName())
                            .toUri();
                    return webClient.get()
                            .uri(url)
                            .retrieve()
                            .bodyToFlux(ForestResponse.class)
                            .map(fr -> new SensorResponse(
                                    sensor.getId(),
                                    System.currentTimeMillis(),
                                    sensor.getForestName(),
                                    fr.temperature()
                            ))
                            // Send every 1 second
                            .doOnNext(sensorProducer::sendJsonMessage)
                            .takeUntil(response -> !sensor.getState());
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    public Mono<SensorDto> get(Integer id) {
        return Mono.fromSupplier(() -> db.get(id))
                .flatMap(s -> {
                    if (s == null) {
                        return Mono.error(new RuntimeException(String.format("Sensor %d is not found!", id)));
                    }
                    return Mono.just(sensorMapper.toSensorDto(s));
                });
    }

    public Mono<SensorDto> update(Integer id, SensorDto req) {
        return Mono.fromSupplier(() -> db.get(id))
                .flatMap(s -> {
                    if (s == null) {
                        return Mono.error(new RuntimeException(String.format("Sensor %d is not found!", req.id())));
                    }
                    var prev = s.makeCopy();
                    sensorMapper.updateSensor(req, s);
                    System.out.println(s);
                    sensorEventSink.tryEmitNext(new ValueHolder<>(prev, s));
                    return Mono.just(sensorMapper.toSensorDto(s));
                });
    }
}
