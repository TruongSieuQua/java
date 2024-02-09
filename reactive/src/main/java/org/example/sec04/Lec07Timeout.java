package org.example.sec04;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class Lec07Timeout {
	public static void main(String[] args) {

		getOrderNumbers()
			.timeout(Duration.ofSeconds(2), fallback())
			.subscribe(Util.subscriber());


		Util.sleepSeconds(60);

	}

	private static long randomDuration(long b, long e) {
		long rd = (new Random()).nextLong(b, e);
		System.out.println("Random number is " + rd);
		return rd;
	}

	private static Flux<Integer> getOrderNumbers() {
		return Flux.range(1, 10)
			.delayElements(Duration.ofSeconds(3));
	}

	private static Flux<Integer> fallback() {
		return Flux.range(100, 10)
			.delayElements(Duration.ofSeconds(5));
	}

}
