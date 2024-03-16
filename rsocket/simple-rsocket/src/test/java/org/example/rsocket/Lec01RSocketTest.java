package org.example.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // @BeforeAll, @AfterAll run one time when instance initial
public class Lec01RSocketTest {

    private RSocket rSocker;

    @BeforeAll
    public void setup(){
        // Create client socket connect to server socket "localhost:6565"
        this.rSocker = RSocketConnector.create()
                .connect(TcpClientTransport.create("localhost", 6565))
                .block();
    }

    @Test
    public void fireAndForget(){
        // client socket fire a payload to server
        Payload payload = DefaultPayload.create("Hello World!");
        Mono<Void> mono = this.rSocker.fireAndForget(payload);

        // Mono nothing happens util you subscribe

        StepVerifier
                .create(mono) // will subscribe a mono
                .verifyComplete();
    }
}
