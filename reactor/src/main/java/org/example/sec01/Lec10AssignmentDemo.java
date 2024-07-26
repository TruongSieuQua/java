package org.example.sec01;

import org.example.sec01.assignment.FileService;
import org.example.utils.Util;

public class Lec10AssignmentDemo {
	public static void main(String[] args) {
		FileService.write("file03.txt", "This is file3")
			.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		FileService.read("file03.txt")
			.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		FileService.delete("file03.txt")
			.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
	}
}
