package com.tjn.graphql.sec01.lec01;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class GraphqlController {

    @QueryMapping("sayHello")
    public Mono<String> sayHello(){
        return Mono.just("Hello world")
                .delayElement(Duration.ofSeconds(1));
    }

    @QueryMapping
    public Mono<String> sayHelloTo(@Argument("name") String value){
        return Mono.fromSupplier(() -> "Hello " + value)
                .delayElement(Duration.ofMillis(500));
    }

    @QueryMapping
    public Mono<Integer> random(){
        return Mono.just(ThreadLocalRandom.current().nextInt(1, 100))
                .delayElement(Duration.ofMillis(1200));
    }
}
