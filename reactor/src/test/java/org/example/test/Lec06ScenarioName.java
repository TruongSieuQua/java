package org.example.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06ScenarioName {

	@Test
	public void test1() {

		Flux<String> flux = Flux.just("a", "b", "c");

		// Set a name for testcase
		StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test");

		StepVerifier.create(flux, scenarioName)
			.expectNextCount(12)
			.verifyComplete();


	}

	@Test
	public void test2() {

		Flux<String> flux = Flux.just("a", "b1", "c");

		StepVerifier.create(flux)
			// Set a name for each step of testcase
			.expectNext("a")
			.as("a-test")
			.expectNext("b")
			.as("b-test")
			.expectNext("c")
			.as("c-test")
			.verifyComplete();


	}

}
