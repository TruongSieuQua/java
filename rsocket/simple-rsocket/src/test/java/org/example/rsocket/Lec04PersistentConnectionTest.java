package org.example.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketClient;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/*
    Persistent connection is connection will close if server socket restarted even though during client sleep or don't receiving data
    To fix it or to make connection auto connect again when server is restart and client don't receiving any data during that time
    Should create a Mono rSocketClient
*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // @BeforeAll, @AfterAll run one time when instance initial
public class Lec04PersistentConnectionTest {
    private RSocketClient rSocketClient;

    @BeforeAll
    public void setup(){
        Mono<RSocket> socketMono = RSocketConnector.create()
                .connect(TcpClientTransport.create("localhost", 6565))
                .doOnNext(r -> System.out.println("Going to connect"));

        this.rSocketClient = RSocketClient.from(socketMono);
    }

    @Test
    public void connectionTest() throws InterruptedException {
        Flux<String> flux = this.rSocketClient
                .requestStream(Mono.just(DefaultPayload.create("")))
                .map(Payload::getDataUtf8)
                // Suppose the client has a slow processing speed of 1 message/s
                // It will make server slow because sever know client process slowly
                // 75% items of server queue size (default 32) is drained by client it will emit more 75% items of queue size
                .delayElements(Duration.ofSeconds(1))
                .take(10)
                .doOnNext(System.out::println);

        StepVerifier.create(flux)
                .expectNextCount(10)
                .verifyComplete();

        System.out.println("Goint to sleep");
        Thread.sleep(10000);
        System.out.println("woke up");

        Flux<String> flux2 = this.rSocketClient
                .requestStream(Mono.just(DefaultPayload.create("")))
                .map(Payload::getDataUtf8)
                // Suppose the client has a slow processing speed of 1 message/s
                // It will make server slow because sever know client process slowly
                // 75% items of server queue size (default 32) is drained by client it will emit more 75% items of queue size
                .delayElements(Duration.ofSeconds(1))
                .take(10)
                .doOnNext(System.out::println);

        StepVerifier.create(flux2)
                .expectNextCount(10)
                .verifyComplete();
    }


}
