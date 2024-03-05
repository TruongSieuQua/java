package org.example.sec08;

import org.example.sec08.helper.AmericanAirlines;
import org.example.sec08.helper.Emirates;
import org.example.sec08.helper.Qatar;
import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec03Merge {
	public static void main(String[] args) {

		Flux<String> merge = Flux.merge(
			Qatar.getFlights(),
			Emirates.getFlights(),
			AmericanAirlines.getFlights()
		);

		merge.subscribe(Util.subscriber());

		Util.sleepSeconds(10);

	}

}
