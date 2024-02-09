package org.example.sec06;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04MultiplePublishOn {
	public static void main(String[] args) {

		Flux<Object> flux = Flux.create(fluxSink -> {
				printThreadName("create");
				for (int i = 0; i < 4; i++) {
					Util.sleepSeconds(2);
					fluxSink.next(i);
				}
				fluxSink.complete();
			})
			.doOnNext(i -> printThreadName("next " + i));


		flux
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> printThreadName("next " + i))
			.publishOn(Schedulers.parallel())
			.subscribe(v -> printThreadName("sub " + v));


		//Util.sleepSeconds(5);

	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
	}
}
