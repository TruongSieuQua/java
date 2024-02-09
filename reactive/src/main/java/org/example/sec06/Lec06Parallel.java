package org.example.sec06;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06Parallel {
	public static void main(String[] args) {
		Flux.range(1, 10)
			.parallel(4) // 4 thread
			.runOn(Schedulers.boundedElastic())
			.doOnNext(i -> printThreadName("next " + i))
			//convert back to single flux
			.sequential()
			.subscribe(v -> printThreadName("sub " + v));

		Util.sleepSeconds(5);

	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
	}
}
