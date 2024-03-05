package org.example.sec01;

import org.example.utils.FileService;
import org.example.utils.Util;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

public class Lec09_FileService {
	public static final Path PATH = FileService.PATH;
	public static final String FILE1 = "file01.txt";
	public static final String FILE2 = "file02.txt";
	public static final String FILE3 = "file03.txt";

	public static void main(String[] args) {
		System.out.println(PATH.getFileName());
		System.out.println(PATH.getRoot());
		System.out.println(PATH.getParent());
		System.out.println(PATH.isAbsolute());
		System.out.println(PATH.resolve(FILE3));

//        FileService.read(FILE1).subscribe(
//                Util.onNext(),
//                Util.onError(),
//                Util.onComplete()
//        );

//        FileService.read(FILE3).subscribe(
//                Util.onNext(),
//                Util.onError(),
//                Util.onComplete()
//        );

		Mono<String> mono = Mono.fromSupplier(() -> {
			throw new RuntimeException("Something went wrong!");
		});

		// Subscribing to the Mono and handling the result or error
		mono.subscribe(
			value -> System.out.println("Received value: " + value),
			error -> System.out.println("Error: ")
		);

//        FileService.write(FILE3, "Write to file doesn't exists").subscribe(
//                Util.onNext(),
//                Util.onError(),
//                Util.onComplete()
//        );
//
//        FileService.delete(FILE3).subscribe(
//                Util.onNext(),
//                Util.onError(),
//                Util.onComplete()
//        );

		Util.sleepSeconds(3);

	}


}
