package com.springrsocket;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec04CallbackTest extends BaseTest {

    @Autowired
    private RSocketMessageHandler handler;

    @BeforeAll
    public void setup() {
        this.requester = this.builder.rsocketConnector(c-> c.acceptor(handler.responder()))
                .transport(TcpClientTransport.create("localhost", 6565));
    }

    @Test
    public void callbackTest() throws InterruptedException {
        Mono<Void> mono = this.requester.route("batch.job.request").data(5).send();

        StepVerifier.create(mono)
                .verifyComplete();

        Thread.sleep(12000);

    }

}
