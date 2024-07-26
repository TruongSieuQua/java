package org.example.sec06;

import org.example.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class TestSubscribeOnCallback {
	public static void main(String[] args) {
		Flux.create(fluxSink -> {
				System.out.println("inside create");
				printThreadName("create");
				for (int i = 0; i < 5; i++) {
					fluxSink.next(i);

				}
				// fluxSink.complete();
				fluxSink.error(new RuntimeException("oops"));
				System.out.println("--completed");
			})
			.doFirst(() -> {
				printThreadName("doFirst (3)");
				System.out.println("doFirst (3)");
			})
			.doFirst(() -> {
				printThreadName("doFirst (2)");
				System.out.println("doFirst (2)");
			})
			.doFirst(() -> {
				printThreadName("doFirst (1)");
				System.out.println("doFirst (1)");
			}) // like stack
			.doOnSubscribe(s -> {
				printThreadName("doOnSubscribe");
				System.out.println("doOnSubscribe" + s);
			})
			.doOnRequest(l -> {
				printThreadName("doOnRequest" + l);
				System.out.println("doOnRequest : " + l);
			})

			// begin Flux.create function
			// loop each time before pub.onNext
			.doOnNext(o -> {
				printThreadName("doOnNext" + o);
				System.out.println("doOnNext : " + o);
			}) //sub receiving
			.take(2) // create cancel signal

			// If take() is called
			.doOnCancel(() -> {
				printThreadName("doOnCancel");
				System.out.println("doOnCancel");
			}) //and emit fluxSink.complete() signal

			//If fluxSink.complete() is called
			.doOnComplete(() -> {
				printThreadName("doOnComplete");
				System.out.println("doOnComplete");
			}) // sub receiving

			//else if fluxSink.error is called
			.doOnError((e) -> {
				printThreadName("doOnError");
				System.out.println("doOnError " + e.getMessage());
			}) // sub receiving

			.doOnTerminate(() -> {
				printThreadName("doOnTerminate");
				System.out.println("doOnTerminate");
			})

			.doFinally(signal -> {
				printThreadName("doFinally 2");
				System.out.println("doFinally 2 : " + signal);
			})
			.doFinally(signal -> {
				printThreadName("doFinally 1");
				System.out.println("doFinally 1 :  " + signal);
			}) //like stack sdasdadssdajjkwqws

			.doOnDiscard(Object.class, o -> {
				printThreadName("doOnDiscard " + o);
				System.out.println("doOnDiscard : " + o);
			})

			.subscribeOn(Schedulers.boundedElastic())
			.subscribeOn(Schedulers.parallel())
			// continue Flux.create function to end
			.subscribe(Util.subscriber());

		Util.sleepSeconds(30);
	}

	private static void printThreadName(String msg) {
		System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
	}
}
