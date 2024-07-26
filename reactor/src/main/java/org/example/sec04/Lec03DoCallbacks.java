package org.example.sec04;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {
	public static void main(String[] args) {
		Flux.create(fluxSink -> {
				System.out.println("inside create");
				for (int i = 0; i < 5; i++) {
					fluxSink.next(i);

				}
				// fluxSink.complete();
				fluxSink.error(new RuntimeException("oops"));
				System.out.println("--completed");
			})
			.doFirst(() -> System.out.println("doFirst (3)"))
			.doFirst(() -> System.out.println("doFirst (2)"))
			.doFirst(() -> System.out.println("doFirst (1)")) // like stack

			.doOnSubscribe(s -> System.out.println("doOnSubscribe" + s))
			.doOnRequest(l -> System.out.println("doOnRequest : " + l))

			// begin Flux.create function
			// loop each time before pub.onNext
			.doOnNext(o -> System.out.println("doOnNext : " + o)) //sub receiving
			.take(2) // create cancel signal

			// If take() is called
			.doOnCancel(() -> System.out.println("doOnCancel")) //and emit fluxSink.complete() signal

			//If fluxSink.complete() is called
			.doOnComplete(() -> System.out.println("doOnComplete")) // sub receiving
			//else if fluxSink.error is called
			.doOnError((e) -> System.out.println("doOnError " + e.getMessage())) // sub receiving

			.doOnTerminate(() -> System.out.println("doOnTerminate"))

			.doFinally(signal -> System.out.println("doFinally 2 : " + signal))
			.doFinally(signal -> System.out.println("doFinally 1 :  " + signal)) //like stack sdasdadssdajjkwqws

			.doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
			// continue Flux.create function to end
			.subscribe(Util.subscriber());
	}
}
