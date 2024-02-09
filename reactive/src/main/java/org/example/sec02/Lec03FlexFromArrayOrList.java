package org.example.sec02;

import org.example.utils.Util;
import reactor.core.publisher.Flux;

public class Lec03FlexFromArrayOrList {
	public static void main(String[] args) {
		// List<String> strings = Arrays.asList("a", "b", "c");
/*        Flux.fromIterable(strings)
                .subscribe(Util.onNext());*/

		Integer[] arr = {2, 5, 7, 8};
		Flux.fromArray(arr)
			.subscribe(Util.onNext());


	}
}
