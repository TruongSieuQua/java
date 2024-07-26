package org.example.sec08;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {
	public static void main(String[] args) {
		Flux<String> flux1 = Flux.just("a", "b");
		Flux<String> flux2 = Flux.error(new RuntimeException("oops"));
		Flux<String> flux3 = Flux.just("c", "d", "e");

		//concat will stop emit when emit an error so change to concatDelayError
		Flux<String> flux = Flux.concatDelayError(flux1, flux2, flux3);


		flux.subscribe(Util.subscriber());

	}
}
