package org.example.sec08.helper;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
	private List<String> list = new ArrayList<>();

	public Flux<String> generateNames() {
		return

			// If startWith Flux below not enough run this flux
			Flux.generate(stringSynchronousSink -> {
					System.out.println("generated fresh");
					Util.sleepSeconds(1);
					String name = Util.faker().name().firstName();
					System.out.println("Generated name: " + name);
					list.add(name);
					stringSynchronousSink.next(name);
				})
				.cast(String.class)
				.startWith(getFromCache()); // combining two Flux start with this Flux
	}

	private Flux<String> getFromCache() {
		return Flux.fromIterable(list);
	}
}
