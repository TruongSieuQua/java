package org.example.sec01;

import org.example.utils.Util;
import reactor.core.publisher.Mono;

public class Lec03_MonoSubscribe {
//    public static void main(String[] args) {
//        Mono<Integer> mono = Mono.just("ball")
//                .map(String::length)
//                .map(l -> l/0);
//
//        mono.subscribe(
//                item -> System.out.println("Emit item = " + item),
//                err -> System.out.println(err.getMessage()),
//                () -> System.out.println("Complete!")
//        );
//    }

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(l -> l/0);

        mono.subscribe(
                Util.onNext(),
                Util.onError(),
               Util.onComplete()
        );
    }
}
