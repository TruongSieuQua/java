package com.tjn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Data
@AllArgsConstructor
public class Forest {
    private String name;
    private String state;
    private Double temperature;
    private double baseTemperature;

    public Forest(String name, double baseTemperature) {
        this.name = name;
        this.state = "normal";  // Default state is 'normal'
        this.baseTemperature = baseTemperature;
        this.temperature = baseTemperature;
    }

    public void changeState(String newState) {
        if (newState.equals("normal") || newState.equals("fired") || newState.equals("extinguish")) {
            this.state = newState;
        } else {
            throw new IllegalArgumentException("Invalid state. State must be 'normal', 'fired', or 'extinguish'.");
        }
    }

    public Flux<Double> temperatureStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> calculateTemperature());
    }

    private double calculateTemperature() {
        switch (state) {
            case "normal":
                if (temperature > baseTemperature + 2) {
                    temperature -= Math.random();
                } else if (temperature < baseTemperature - 2) {
                    temperature += Math.random();
                } else {
                    temperature = baseTemperature + Math.random() * 4 - 2;
                }
                break;
            case "fired":
                temperature += Math.random() * 3 + 1;
                break;
            case "extinguish":
                temperature -= Math.random() * 3 + 1;
                if (temperature < 25) {
                    this.state = "normal";
                }
                break;
        }
        return temperature;
    }
//    public static void main(String[] args) throws InterruptedException {
//        Forest forest = new Forest("Amazon", 25.0);
//        forest.setState("fired");
//        forest.temperatureStream()
//                .doOnNext(temp -> System.out.println("Forest State: " + forest.state + ", Temperature: " + temp))
//                .subscribe();
//
//        Flux.interval(Duration.ofSeconds(5))
//                .doOnNext((i) -> forest.setState("extinguish")).subscribe();
//
//        Thread.sleep(10000);
//    }
}
