package com.example.selenium;

import com.example.selenium.model.Television;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

class SeleniumApplicationTests extends SpringBaseTest{

	@Value("PATH")
	private String path;

	@Value("${fruits}")
	private List<String> fruits;

	@Value("${TEST_URL:https://www.google.com}")
	private String testUrl;

	@Autowired
	private Television tv;

	@Autowired
	private Faker faker;

	@Test
	void contextLoads() {
		System.out.println("Inside contextLoads method");
		tv.playMovie();

	}

}
