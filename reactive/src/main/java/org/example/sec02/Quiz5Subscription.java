package org.example.sec02;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Quiz5Subscription {
	public static void main(String[] args) {
		Flux.range(1, 10)
			.subscribeWith(new Subscriber<Integer>() {

				private Subscription subscription;

				@Override
				public void onSubscribe(Subscription subscription) {
					this.subscription = subscription;
				}

				@Override
				public void onNext(Integer next) {
					System.out.println(next);
				}

				@Override
				public void onError(Throwable throwable) {

				}

				@Override
				public void onComplete() {
					System.out.println("done");
				}

			});
	}
}
