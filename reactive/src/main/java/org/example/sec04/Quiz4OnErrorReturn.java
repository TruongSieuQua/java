package org.example.sec04;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Quiz4OnErrorReturn {
	public static void main(String[] args) {
		Flux.range(1, 3)
			.map(i -> i / (2 - i))
			.onErrorReturn(3) //If error emit 3 and emit complete signal
			.subscribe(Util.subscriber());
	}
}
