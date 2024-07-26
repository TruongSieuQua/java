package org.example.sec02;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {
	public static void main(String[] args) {
		List<Integer> list = List.of(1, 2, 3, 4, 5);
		Stream<Integer> stream = list.stream();

		// stream.forEach(System.out::println); // closed
		// stream.forEach(System.out::println);

		//Not recommendation way, each time subscribe it will call stream and get data just first time
		//Flux<Integer> integerFlux = Flux.fromStream(stream);

		//Better approach data from stream will store in flux
		Flux<Integer> integerFlux = Flux.fromStream(() -> list.stream());

		integerFlux
			.subscribe(
				Util.onNext(),
				Util.onError(),
				Util.onComplete()
			);

		integerFlux
			.subscribe(
				Util.onNext(),
				Util.onError(),
				Util.onComplete()
			);
	}
}
