package com.springrsocket;

import com.springrsocket.dto.ComputationRequestDto;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.rsocket.RSocketServerAutoConfiguration"
})
public class Lec07ConnectionManagerTest {

    @Autowired
    private RSocketRequester.Builder builder;

    // Math no event test
    @Test
    public void connectionManagerTest() throws InterruptedException {
        RSocketRequester requester1 = this.builder
                .transport(TcpClientTransport.create("localhost", 6565));
        RSocketRequester requester2 = this.builder
                .transport(TcpClientTransport.create("localhost", 6565));

        requester1.route("math.service.print").data(new ComputationRequestDto(5)).send().subscribe();
        requester2.route("math.service.print").data(new ComputationRequestDto(6)).send().subscribe();

        Thread.sleep(8000);
    }

    // math event test
    @Test
    public void mathEventTest() throws InterruptedException {
        RSocketRequester requester1 = this.builder
                .transport(TcpClientTransport.create("localhost", 6565));
        RSocketRequester requester2 = this.builder
                .setupRoute("math.events.connection")
                .transport(TcpClientTransport.create("localhost", 6565));

        requester1.route("math.service.print").data(new ComputationRequestDto(5)).send().subscribe();
        requester2.route("math.service.print").data(new ComputationRequestDto(6)).send().subscribe();

        Thread.sleep(8000);
    }

}
