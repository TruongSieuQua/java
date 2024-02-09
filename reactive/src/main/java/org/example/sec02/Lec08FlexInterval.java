package org.example.sec02;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08FlexInterval {
	public static void main(String[] args) {
		Flux.interval(Duration.ofSeconds(1))
			.subscribe(Util.onNext());

		Util.sleepSeconds(5);
	}
}
