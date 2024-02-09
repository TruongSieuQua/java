package org.example.utils;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util {

	static Faker faker = new Faker();

	public static Faker faker() {
		return faker;
	}

	public static Consumer<Object> onNext() {
		return o -> System.out.println("Received: " + o);
	}

	public static Consumer<Throwable> onError() {
		return (error) -> {
			System.out.println("Error: " + error.getMessage());
		};
	}

	public static Runnable onComplete() {
		return () -> System.out.println("Completed!");
	}

	public static void sleepSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static Subscriber<Object> subscriber() {
		return new DefaultSubscriber();
	}

	public static Subscriber<Object> subscriber(String name) {
		return new DefaultSubscriber(name);
	}
}
