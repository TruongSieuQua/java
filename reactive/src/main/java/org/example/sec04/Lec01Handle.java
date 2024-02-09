package org.example.sec04;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
	public static void main(String[] args) {
		// handle = filter + map
		Flux.range(1, 20)
			.handle((integer, synchronousSink) -> {
				if (integer == 7)
					synchronousSink.complete();
				else
					synchronousSink.next(integer);
			})
			.subscribe(Util.subscriber());
	}
}
