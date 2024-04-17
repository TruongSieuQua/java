package com.springrsocket;

import com.springrsocket.dto.ClientConnectionRequest;
import com.springrsocket.dto.ComputationRequestDto;
import com.springrsocket.dto.ComputationResponseDto;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import reactor.test.StepVerifier;

import java.util.concurrent.ThreadLocalRandom;

public class Lec06ConnectionSetupTest extends BaseTest{

    @BeforeAll
    public void setup(){
        ClientConnectionRequest request = new ClientConnectionRequest();
        request.setClientId("order-service");
        request.setSecretKey("password");

        this.requester = this.builder.setupData(request)
                .transport(TcpClientTransport.create("localhost", 6565));
    }

    @RepeatedTest(3)
    public void connectionTest(){
        var mono = this.requester.route("math.service.square")
                .data(new ComputationRequestDto(ThreadLocalRandom.current().nextInt(1, 50)))
                .retrieveMono(ComputationResponseDto.class)
                .doOnNext(System.out::println);

        StepVerifier.create(mono)
                .expectNextCount(1)
                .verifyComplete();
    }

}
