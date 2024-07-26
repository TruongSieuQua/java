package org.example.sec04;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Delay {
	public static void main(String[] args) {

		System.setProperty("reactor.bufferSize.x", "9");

		Flux.range(1, 100)  // emit immediately 32 items
			.log()
			.delayElements(Duration.ofSeconds(1)) // delay each item for one second
			.subscribe(Util.subscriber());


		Util.sleepSeconds(60);
	}
}
