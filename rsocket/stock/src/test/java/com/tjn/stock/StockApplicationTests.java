package com.tjn.stock;

import com.tjn.stock.dto.StockTickDto;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.test.StepVerifier;

@SpringBootTest
class StockApplicationTests {

	@Autowired
	private RSocketRequester.Builder builder;

	@Test
	void stockPriceTest(){
		RSocketRequester requester = this.builder
				.transport(TcpClientTransport.create("localhost", 7070));

		var flux = requester.route("stock.ticks")
				.retrieveFlux(StockTickDto.class)
				.doOnNext(System.out::println)
				.take(12);

		StepVerifier.create(flux)
				.expectNextCount(12)
				.verifyComplete();
	}

	@Test
	void contextLoads() {
	}

}
