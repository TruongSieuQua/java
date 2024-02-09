package org.example.sec02;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09MonoToFlux {
	public static void main(String[] args) {

		//Mono to Flux
		Mono<String> mono = Mono.just("a");
		Flux<String> flux = Flux.from(mono);
		flux.subscribe(Util.onNext());

		//Flux to Mono
		Flux.range(1, 10)
			.next() // 1
			.filter(i -> i > 3)
			.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
	}

	private static void doSomething(Flux<String> flux) {

	}

}
