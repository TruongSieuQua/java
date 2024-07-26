package org.example.sec05;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Quiz4FluxCreateWithShare {
	public static void main(String[] args) {
		Flux<Object> flux = Flux.create(fluxSink -> {
				System.out.println("created");
				for (int i = 0; i < 5; i++) {
					fluxSink.next(i);
				}
				fluxSink.complete();
			})
			.delayElements(Duration.ofSeconds(2)) //Meaning discard all elements emit before 2 seconds
			.publish()
			.refCount(2);
		//.autoConnect(2); // Chi co 2 consumer nhan dc

		flux.subscribe(Util.subscriber("a"));
		flux.subscribe(Util.subscriber("b"));
		flux.subscribe(Util.subscriber("c"));
		flux.subscribe(Util.subscriber("d"));
		flux.subscribe(Util.subscriber("e"));
		flux.subscribe(Util.subscriber("f"));
		flux.subscribe(System.out::println);

		Util.sleepSeconds(40);
	}
}
