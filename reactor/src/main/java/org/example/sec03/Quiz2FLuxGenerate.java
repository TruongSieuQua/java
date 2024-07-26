package org.example.sec03;

import reactor.core.publisher.Flux;

public class Quiz2FLuxGenerate {

	public static void main(String[] args) {
		Flux.generate(synchronousSink -> {

				synchronousSink.next(1);
				// Caused by: java.lang.IllegalStateException: More than one call to onNext
				synchronousSink.next(2);

			})
			.subscribe(System.out::println);
	}

}
