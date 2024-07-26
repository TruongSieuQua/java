package org.example.sec07;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03BackpressureLatest {
	public static void main(String[] args) {
		// 75% 12
		// always push 1 latest item to cache
		System.setProperty("reactor.bufferSize.small", "16");

		Flux.create(fluxSink -> {
				for (int i = 1; i < 201; i++) {
					fluxSink.next(i);
					System.out.println("Pushed : " + i);
					Util.sleepMillis(1);
				}
				fluxSink.complete();
			})
			.onBackpressureLatest()
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> {
				Util.sleepMillis(10);
			})
			.subscribe(Util.subscriber());

		Util.sleepSeconds(10);
	}
}
