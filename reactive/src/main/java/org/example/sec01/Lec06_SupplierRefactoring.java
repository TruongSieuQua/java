package org.example.sec01;

import com.github.javafaker.Faker;
import org.example.utils.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec06_SupplierRefactoring {
    public static void main(String[] args) {
        getName();// return pipeline
        getName(); //return pipeline
        getName(); //return pipeline

        System.out.println("Execute pipeline blocking total = 30s");
        getName().subscribe(Util.onNext());
        getName().subscribe(Util.onNext());
        getName().subscribe(Util.onNext());

        System.out.println("Execute non-blocking total = 11s");
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        Util.sleepSeconds(11);

        System.out.println("Execute non-blocking to blocking total = 30s");
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .block();

        System.out.println("Continue....");
    }

    private static Mono<String> getName(){
        System.out.println("Entered getName method");
        //Building a pipeline != execute a pipeline
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name....");
            Util.sleepSeconds(10);
            return Faker.instance().name().fullName();
        }).map(String::toUpperCase);

    }
}
