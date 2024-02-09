package org.example.sec03;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec05FlexGenerate {
	public static void main(String[] args) {
		//Will run lamda again and again five times
		Flux.generate(synchronousSink -> {
				System.out.println("emitting");
				synchronousSink.next(Util.faker().country().name());
				//synchronousSink.complete();
				//synchronousSink.error(new RuntimeException("oops"));
			})
			.take(5)
			.subscribe(Util.subscriber());
	}
}
