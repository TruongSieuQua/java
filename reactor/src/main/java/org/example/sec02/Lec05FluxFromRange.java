package org.example.sec02;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxFromRange {
	public static void main(String[] args) {
		Flux.range(3, 10)
			.log()
			.map(i -> Util.faker().name().fullName())
			.log()
			.subscribe(
				Util.onNext()
			);
	}
}
