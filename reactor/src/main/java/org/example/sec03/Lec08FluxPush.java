package org.example.sec03;

import org.example.sec03.helper.NameProducer;
import org.example.utils.Util;

public class Lec08FluxPush {
	public static void main(String[] args) {
		NameProducer nameProducer = new NameProducer();

		// Flux push is single-thread so not a thread safe
		// Flux.create(nameProducer)
		//		.subscribe(Util.subscriber());

		// Flux create is thread safe
		// Flux.create(nameProducer)
		//		.subscribe(Util.subscriber());

		Runnable runnable = nameProducer::produce;

		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}

		Util.sleepSeconds(2);

	}
}
