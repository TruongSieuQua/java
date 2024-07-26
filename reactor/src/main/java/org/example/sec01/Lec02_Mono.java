package org.example.sec01;

import reactor.core.publisher.Mono;

public class Lec02_Mono {
    public static void main(String[] args) {

        //publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        mono.subscribe(i -> System.out.println("Received i = " + i));
    }
}
