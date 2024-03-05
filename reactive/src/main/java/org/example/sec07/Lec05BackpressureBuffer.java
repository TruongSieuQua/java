package org.example.sec07;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05BackpressureBuffer {
	public static void main(String[] args) {
		// 75% 12
		System.setProperty("reactor.bufferSize.small", "16");

		Flux.create(fluxSink -> {
				for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
					fluxSink.next(i);
					System.out.println("Pushed : " + i);
					Util.sleepMillis(1);
				}
				fluxSink.complete();
			})
			// Ex: sub receive 57 and send onCancel signal, pub is push 58 59 so 58 59 will be handled by below function
			.onBackpressureBuffer(50, o -> System.out.println("Dropped : " + o))
			.publishOn(Schedulers.boundedElastic())

			.doOnNext(i -> {
				Util.sleepMillis(10);
			})
			.subscribe(Util.subscriber());


		Util.sleepSeconds(10);


	}
}
