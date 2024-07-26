package org.example.sec07;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec02BackPressureDrop {
	public static void main(String[] args) {
		// when sub consume 75% (12) it will use 12 to cache next 12 item pub emit
		// When sub consumed 25% (4) remaining plus 50% (8) it will use 12 to cache next 12 item
		// So each subscribe cache capacity is 16 item.
		//That why when subscriber receive 12, 48 or else pushed to buffer (or queue) so next time subscriber receive 48 or else
		System.setProperty("reactor.bufferSize.small", "16");

		List<Object> list = new ArrayList<>();

		Flux.create(fluxSink -> {
				for (int i = 1; i < 201; i++) {
					fluxSink.next(i);
					System.out.println("Pushed : " + i);
					Util.sleepMillis(1);
				}
				fluxSink.complete();
			})
			// With drop strategy, When sub receive 12 items,
			// items before 48 or else will be dropped
			.onBackpressureDrop(list::add)
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> {
				Util.sleepMillis(10);
			})
			.subscribe(Util.subscriber());


		Util.sleepSeconds(10);
		System.out.println(list);

	}
}
