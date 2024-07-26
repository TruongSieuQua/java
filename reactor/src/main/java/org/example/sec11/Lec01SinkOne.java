package org.example.sec11;

import org.example.utils.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {
	public static void main(String[] args) {

		// mono 1 value / empty / error
		// Type: One
		// Behavior: Mono
		// Pub-Sub: 1-N
		Sinks.One<Object> sink = Sinks.one();

		Mono<Object> mono = sink.asMono();

		mono.subscribe(Util.subscriber("sam"));
		sink.tryEmitValue("Hello");
		Util.sleepSeconds(2);
		mono.subscribe(Util.subscriber("mike"));


/*
				sink.emitValue("hi", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            // not retry when error
            return false;
        });
				// can't emit items when already emitted complete signal
        sink.emitValue("hello", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });
  */
	}
}
