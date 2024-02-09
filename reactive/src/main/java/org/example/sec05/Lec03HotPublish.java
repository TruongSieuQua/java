package org.example.sec05;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {
	public static void main(String[] args) {
		// Same as share()
		//When have more than or equals 2 subcribe it will emit item
		//Warning: Begin emitting when have min Subscriber
		//Warning: When down to 0 or 1 and up to 2 again it will repeatedly emit item
		Flux<String> movieStream = Flux.fromStream(() -> getMovie())
			.delayElements(Duration.ofSeconds(2))
			.publish()
			.refCount(2);

		movieStream
			.subscribe(Util.subscriber("sam"));

		Util.sleepSeconds(5);

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
