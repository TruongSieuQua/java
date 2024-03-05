package org.example.sec08;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Quiz2StartWith {
	public static void main(String[] args) {
		Flux<String> flux = Flux.just("a", "b", "c");
		flux.startWith(flux)
			.subscribe(Util.subscriber());
	}
}
