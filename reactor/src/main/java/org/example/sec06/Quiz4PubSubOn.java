package org.example.sec06;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Quiz4PubSubOn {
	public static void main(String[] args) {
		// parallel thread
		Flux.create(fluxSink -> {
				for (int i = 0; i < 5; i++) {
					fluxSink.next(i);
				}
				fluxSink.complete();
			})
			.map(i -> i + "a")
			.publishOn(Schedulers.boundedElastic())
			.subscribeOn(Schedulers.parallel())
			// elastic thread
			.subscribe(Util.subscriber());
	}
}
