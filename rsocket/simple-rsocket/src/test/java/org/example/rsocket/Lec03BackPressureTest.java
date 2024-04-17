package org.example.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.example.rsocket.client.CallbackService;
import org.example.rsocket.dto.RequestDto;
import org.example.rsocket.util.ObjectUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // @BeforeAll, @AfterAll run one time when instance initial
public class Lec03BackPressureTest {

    private RSocket rSocket;

    @BeforeAll
    public void setup(){
        this.rSocket = RSocketConnector.create()
                .connect(TcpClientTransport.create("localhost", 6565))
                .block();
    }

    @Test
    public void backPressure() throws InterruptedException {
        Flux<String> mono = this.rSocket.requestStream(DefaultPayload.create(""))
                .map(Payload::getDataUtf8)
                // Suppose the client has a slow processing speed of 1 message/s
                // It will make server slow because sever know client process slowly
                // 75% items of server queue size (default 32) is drained by client it will emit more 75% items of queue size
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(System.out::println);

        StepVerifier.create(mono)
                .expectNextCount(1000)
                .verifyComplete();
    }

}
