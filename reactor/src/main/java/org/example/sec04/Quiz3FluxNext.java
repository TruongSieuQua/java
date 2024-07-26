package org.example.sec04;

import reactor.core.publisher.Flux;

public class Quiz3FluxNext {
	public static void main(String[] args) {
		Flux.range(1, 10)
			.filter(i -> i > 5)
			.take(2)
			.next() //Like take 1
			.subscribe(System.out::println); // 6
	}
}
