package org.example.sec08;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Quiz3Merge {
	public static void main(String[] args) {
		Mono<Integer> mono1 = Mono.just(1).delayElement(Duration.ofSeconds(1));
		Flux<Integer> flux1 = Flux.just(2);
		Flux<Integer> flux2 = flux1.map(i -> i + 1).delayElements(Duration.ofMillis(500));

		Flux.merge(mono1, flux1, flux2)
			.subscribe(Util.subscriber());

		Util.sleepSeconds(3);
	}

}
