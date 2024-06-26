package org.example.sec05;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec02HotShare {
	public static void main(String[] args) {

		Flux<String> movieStream = Flux.fromStream(() -> getMovie())
			.delayElements(Duration.ofSeconds(1))
			.share();

		movieStream
			.subscribe(Util.subscriber("sam"));

		Util.sleepSeconds(10);

		movieStream
			.subscribe(Util.subscriber("mike"));


		Util.sleepSeconds(60);


	}

	// movie-theatre
	private static Stream<String> getMovie() {
		System.out.println("Got the movie streaming req");
		return Stream.of(
			"Scene 1",
			"Scene 2",
			"Scene 3",
			"Scene 4",
			"Scene 5",
			"Scene 6",
			"Scene 7"
		);
	}
}
