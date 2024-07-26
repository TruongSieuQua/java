package org.example.sec01;

import com.github.javafaker.Faker;
import org.example.utils.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public class Lec05_MonoFromSupplier {
    public static void main(String[] args) {

        //Bad practices
        //Because it will invoke function immediately
        //Use just only when you have data
        Mono<String> monoWhenHaveData = Mono.just(getName());

        //Best practices
        //Thing should be lazy
        //invoke function when subscribe
        Mono<String> monoSupplier = Mono.fromSupplier(()-> getName());
        monoSupplier.subscribe(
                Util.onNext()
        );

        Callable<String> callable = () -> getName();
        Mono.fromCallable(callable)
                .subscribe(Util.onNext());
    }

    public static String getName(){
        System.out.println("Generating name...");
        return Faker.instance().name().firstName();
    }
}
