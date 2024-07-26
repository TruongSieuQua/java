package org.example.sec06;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02MultipleSubscribeOn {
	public static void main(String[] args) {

		// Schedule-1 thread
		Flux<Object> flux = Flux.create(fluxSink -> {
				printThreadName("create");
				fluxSink.next(1);
			})
			.subscribeOn(
				Schedulers.newParallel("vins")
			)
			.doOnNext(i -> printThreadName("next " + i));

		Runnable runnable = () -> flux
			// Schedule-2 thread
			.doFirst(() -> printThreadName("first2"))
			// Mac dinh thread nay se bat dau truoc flux create
			.subscribeOn(Schedulers.boundedElastic()) // Schedule-2
			// Main thead
			.doFirst(() -> printThreadName("first1"))
			// Schedule-1 thread
			.subscribe(v -> printThreadName("sub " + v));

		for (int i = 0; i < 1; i++) {
			new Thread(runnable).start();
		}

		Util.sleepSeconds(5);

	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
	}

}
