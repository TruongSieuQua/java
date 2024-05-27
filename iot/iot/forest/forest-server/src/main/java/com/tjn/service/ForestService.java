package com.tjn.service;

import com.tjn.dto.ForestResponse;
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
    public void init(){
        Forest one = new Forest("1", 30);
        Forest two = new Forest("2", 24);
        Forest three = new Forest("3", 40);
        this.db = Map.of(
                "1", one,
                "2", two,
                "3", three
        );
    }

    public Flux<ForestResponse> getTemperatureStream(String forestName){
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

    public Mono<Void> changeState(String forestName, String newState) {
        Forest forest = this.db.get(forestName);

        if (forest == null) {
            return Mono.error(new IllegalArgumentException("Forest not found: " + forestName));
        }

        try {
            forest.changeState(newState);
            return Mono.empty();  // Indicate successful completion
        } catch (IllegalArgumentException e) {
            return Mono.error(e);  // Return an error if state change is invalid
        }
    }


}