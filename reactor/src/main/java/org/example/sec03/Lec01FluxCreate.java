package org.example.sec03;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
	public static void main(String[] args) {
		Flux.create(fluxSink -> {
				String country;
				do {
					country = Util.faker().country().name();
					fluxSink.next(country);
				} while (!country.toLowerCase().equals("canada"));
				fluxSink.complete();
			}) // keep emit data till emit "canada" even though subscriber canceled
			.take(3) //Cancel after 3
			.subscribe(Util.subscriber());
	}
}
