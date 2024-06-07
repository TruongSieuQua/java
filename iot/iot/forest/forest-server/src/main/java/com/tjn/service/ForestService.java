package com.tjn.service;

import com.tjn.dto.ForestResponse;
import com.tjn.dto.UpdateForestStateDto;
import com.tjn.mapper.ForestMapper;
import com.tjn.model.Forest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ForestService {

    private final ForestMapper forestMapper;

    private Map<String, Forest> db;


    @PostConstruct
    public void init() {
        Forest one = new Forest("f1", 30);
        Forest two = new Forest("f2", 20);
        Forest three = new Forest("f3", 40);
        this.db = Map.of(
                one.getName(), one,
                two.getName(), two,
                three.getName(), three
        );
    }

    public Flux<ForestResponse> getTemperatureStream(String forestName) {
        Forest forest = this.db.get(forestName);
        if (forest == null) {
            return Flux.error(new IllegalArgumentException("Forest not found: " + forestName));
        }
        return forest.temperatureStream()
                .map(temp -> new ForestResponse(forest.getName(), forest.getTemperature()))
                .onErrorResume(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return Flux.empty();
                });
    }

    public Mono<ForestResponse> changeState(String forestName, UpdateForestStateDto req) {
        return Mono.fromSupplier(() -> {
            Forest forest = this.db.get(forestName);
            if (forest == null) {
                throw new IllegalArgumentException("Forest not found: " + forestName);
            }
            forest.changeState(req.state());
            System.out.println("changeState jdkjaskdmkmsd");
            System.out.println(forest);
            return forestMapper.toForestResponse(forest);
        });
    }
}
