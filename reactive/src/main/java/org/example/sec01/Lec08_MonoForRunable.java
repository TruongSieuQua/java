package org.example.sec01;

import org.example.utils.Util;
import reactor.core.publisher.Mono;

public class Lec08_MonoForRunable {
    public static void main(String[] args) {
        Mono.fromRunnable(timeCosummingRunnable()).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    public static Runnable timeCosummingRunnable(){
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operation completed!");
        };
    }
}
