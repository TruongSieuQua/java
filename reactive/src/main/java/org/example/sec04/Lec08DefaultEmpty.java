package org.example.sec04;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec08DefaultEmpty {
	public static void main(String[] args) {
		getOrderNumbers()
			.filter(i -> i > 10) // If pub doesn't emit any item, it will emit default value
			.defaultIfEmpty(-100)
			.subscribe(Util.subscriber());

	}

	private static Flux<Integer> getOrderNumbers() {
		return Flux.range(1, 10);
	}
}
