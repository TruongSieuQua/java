package org.example.sec04;

import reactor.core.publisher.Flux;

public class Quiz2FluxIsImmutable {
	public static void main(String[] args) {
		//Immutable
		//Flux<Integer> range = Flux.range(1, 10);
		//range.map(i -> i * 10); // It will no change still 1 to 10
		//range.subscribe(System.out::println);

		Flux<Integer> range1 = Flux.range(1, 10).map(i -> i * 10);
		range1.subscribe(System.out::println);

		//Hoac
		Flux<Integer> range2 = Flux.range(1, 10);
		range2.map(i -> i * 10).subscribe(System.out::println);

	}
}
