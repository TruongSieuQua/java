package com.springrsocket;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected RSocketRequester requester;

    @Autowired
    protected RSocketRequester.Builder builder;

    @BeforeAll
    public void setup() {
        requester = builder.transport(TcpClientTransport.create("localhost", 6565));
    }
}
