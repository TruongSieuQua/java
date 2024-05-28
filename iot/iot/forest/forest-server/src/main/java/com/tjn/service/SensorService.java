package com.tjn.service;

import com.tjn.config.ServiceUrlConfig;
import com.tjn.dto.ForestResponse;
import com.tjn.dto.SensorDto;
import com.tjn.dto.SensorResponse;
import com.tjn.mapper.SensorMapper;
import com.tjn.model.Sensor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.net.URI;
import java.time.Duration;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SensorService {

    private Map<Integer, Sensor>  db;

    private final SensorMapper sensorMapper;

    private final WebClient webClient;

    private final ServiceUrlConfig serviceUrlConfig;

    private Sinks.Many<SensorResponse> sink;

    @PostConstruct
    public void init(){
        Sensor s1 = new Sensor(1, false,"f1");
        Sensor s2 = new Sensor(2, false, "f2");
        Sensor s3 = new Sensor(3, false, "f3");

        this.db = Map.of(
                s1.getId(), s1,
                s2.getId(), s2,
                s3.getId(), s3
        );
    }

    private void sensorDataToSink(){
        Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> Flux.fromIterable(db.values()))
                .distinctUntilChanged(Sensor::getState)
                .filter(Sensor::getState)
                .flatMap(this::startDataStream)
                .subscribe(sink::tryEmitNext);
    }
    private Flux<SensorResponse> startDataStream(Sensor sensor) {
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
                ));
    }

    public Flux<SensorResponse> fetchTemperatureStreamAndSendToKafka() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
        sensorDataToSink();
        return sink.asFlux();
    }

    public Mono<SensorDto> update(Integer id, SensorDto req){
        return Mono.fromSupplier(() -> db.get(id))
                .flatMap(s -> {
                    if (s == null) {
                        return Mono.error(new RuntimeException(String.format("Sensor %d is not found!", req.id())));
                    }
                    s.setState(req.state());
                    s.setForestName(req.forestName());
                    return Mono.just(sensorMapper.toSensorDto(s));
                });
    }
}
