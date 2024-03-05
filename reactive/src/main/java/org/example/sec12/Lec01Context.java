package org.example.sec12;

import org.example.utils.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {

	public static void main(String[] args) {

		getWelcomeMessage()
			.contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
			// context available on up stream, but not in down stream
			.contextWrite(Context.of("user", "sam"))
			.subscribe(Util.subscriber());

	}


	private static Mono<String> getWelcomeMessage() {
		return Mono.deferContextual(ctx -> {
			if (ctx.hasKey("user")) {
				return Mono.just("Welcome " + ctx.get("user"));
			} else {
				return Mono.error(new RuntimeException("unauthenticated"));
			}
		});
	}

}
