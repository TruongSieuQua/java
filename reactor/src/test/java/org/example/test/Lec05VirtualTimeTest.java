package org.example.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05VirtualTimeTest {

	@Test
	public void test1() {
		StepVerifier.withVirtualTime(() -> timeConsumingFlux())
			// simulate 30s passed so test is done fast
			.thenAwait(Duration.ofSeconds(30))
			.expectNext("1a", "2a", "3a", "4a")
			.verifyComplete();
	}

	@Test
	public void test2() {
		StepVerifier.withVirtualTime(() -> timeConsumingFlux())

			.expectSubscription() // First event happens. sub is an event
			// After sub event No item (event) emitted in first 4 seconds after subscribe
			.expectNoEvent(Duration.ofSeconds(4))

			.thenAwait(Duration.ofSeconds(20))
			.expectNext("1a", "2a", "3a", "4a")
			.verifyComplete();
	}


	private Flux<String> timeConsumingFlux() {
		return Flux.range(1, 4)
			.delayElements(Duration.ofSeconds(5))
			.map((i -> i + "a"));
	}


}
