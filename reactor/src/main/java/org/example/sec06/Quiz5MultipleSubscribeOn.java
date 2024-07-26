package org.example.sec06;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Quiz5MultipleSubscribeOn {
	public static void main(String[] args) {
		// elastic thread
		Flux<Object> flux = Flux.create(fluxSink -> {
				for (int i = 0; i < 5; i++) {
					fluxSink.next(i);
				}
				fluxSink.complete();
			})
			.subscribeOn(Schedulers.boundedElastic());

		flux
			// parallel thread
			.subscribeOn(Schedulers.parallel())
			// elastic thread
			.map(i -> i + "a")
			.subscribe(Util.subscriber());
	}
}
