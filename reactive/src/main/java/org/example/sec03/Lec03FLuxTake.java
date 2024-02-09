package org.example.sec03;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec03FLuxTake {
	public static void main(String[] args) {
		// map
		// filter
		Flux.range(1, 10)
			.log()
			.take(3) // after 3 cancels upstream emit complete to downstream
			.log()
			.subscribe(Util.subscriber());

	}
}
