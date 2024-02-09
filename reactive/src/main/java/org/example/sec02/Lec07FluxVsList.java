package org.example.sec02;

import org.example.sec02.helper.NameGenerator;
import org.example.utils.Util;

public class Lec07FluxVsList {
	public static void main(String[] args) {

		//Have to wait 5s to get result a list
		//List<String> name = NameGenerator.getNames(5);
		//proceed result a list
		//System.out.println(name);

		//Have each element each 1s
		NameGenerator.getNames(5).subscribe(
			//proceed result a element
			Util.onNext()
		);

	}
}
