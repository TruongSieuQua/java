package org.example.sec12;

import org.example.sec12.helper.BookService;
import org.example.sec12.helper.UserService;
import org.example.utils.Util;
import reactor.util.context.Context;

public class Lec02CtxRateLimiterDemo {
	public static void main(String[] args) {

		BookService.getBook()
			.repeat(3)
			// userCategoryContext() just a function add key value to context
			.contextWrite(UserService.userCategoryContext())
			// initialize context. It will run first
			.contextWrite(Context.of("user", "mike"))
			.subscribe(Util.subscriber());

	}
}
