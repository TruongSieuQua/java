package org.example.sec04;

import org.example.sec04.helper.OrderService;
import org.example.sec04.helper.UserService;
import org.example.utils.Util;

import java.io.BufferedReader;

public class Lec12FlatMap {
	public static void main(String[] args) {

		BufferedReader reader;


		UserService.getUsers()
			//flatMap use instead of Map when return a flux<Object> instead of normally Object
			.flatMap(user -> OrderService.getOrders(user.getUserId())) // mono / flux
			// .filter(p -> p > 10)
			.subscribe(Util.subscriber());


		Util.sleepSeconds(60);


	}
}
