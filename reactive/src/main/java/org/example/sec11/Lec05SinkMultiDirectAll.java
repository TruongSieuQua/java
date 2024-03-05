package org.example.sec11;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05SinkMultiDirectAll {
	public static void main(String[] args) {

		System.setProperty("reactor.bufferSize.small", "16");

		// handle through which we would push items

		// Mile will not affect Sam performance
		//Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();

		// Mike will affect Sam performance
		Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

		// handle through which subscribers will receive items
		Flux<Object> flux = sink.asFlux();

		// Sam is fast
		flux.subscribe(Util.subscriber("sam"));

		// Mike is slow
		flux.delayElements(Duration.ofMillis(50)).subscribe(Util.subscriber("mike"));

		for (int i = 0; i < 100; i++) {
			System.out.println("number " + i);
			sink.tryEmitNext(i);
		}

		Util.sleepSeconds(10);

	}
}
