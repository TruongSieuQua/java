package org.example.sec01;

import com.github.javafaker.Faker;
import org.example.utils.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07_MonoFromFuture {
    public static void main(String[] args) {
        Mono.fromFuture(getName())
                .subscribe(Util.onNext());
        Mono.fromFuture(getName())
                .subscribe(Util.onNext());
        Mono.fromFuture(getName())
                .subscribe(Util.onNext());
        System.out.println("Non-blocking");
        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()-> Faker.instance().name().fullName());
    }
}
