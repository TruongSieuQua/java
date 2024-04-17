package com.springrsocket;

import com.springrsocket.dto.ComputationRequestDto;
import com.springrsocket.dto.ComputationResponseDto;
import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Lec13SecurityTest {

    //Mime type (Multipurpose Internet Mail Extensions type) là một chuỗi nhận dạng định dạng của dữ liệu.
    // Mime type giúp các ứng dụng biết cách xử lý dữ liệu mà chúng nhận được
    private final MimeType mimeType = MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());

    @Autowired
    private RSocketRequester.Builder builder;

    private RSocketRequester requester;

    @BeforeAll
    public void connectionSetup(){
        UsernamePasswordMetadata metadata = new UsernamePasswordMetadata("client", "password");
        // Setup auth connection between server1 and server2
        this.requester = this.builder
                .setupMetadata(metadata, mimeType)
                .transport(TcpClientTransport.create("localhost", 6565));
    }

    @Test
    public void requestResponse(){
        // unique user credentials
        UsernamePasswordMetadata credentials = new UsernamePasswordMetadata("user", "password");

        // raise RejectedSetupException, ClosedChannelException when route to endpoint requires authentication
        Mono<ComputationResponseDto> mono = requester.route("math.service.secured.square")
                .metadata(credentials, mimeType)
                .data(new ComputationRequestDto(5))
                .retrieveMono(ComputationResponseDto.class)
                .doOnNext(System.out::println);

        StepVerifier.create(mono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void requestStream(){
        // unique user credentials
        UsernamePasswordMetadata credentials = new UsernamePasswordMetadata("admin", "password");

        // raise RejectedSetupException, ClosedChannelException when route to endpoint requires authentication
        Flux<ComputationResponseDto> mono = requester.route("math.service.secured.table")
                .metadata(credentials, mimeType)
                .data(new ComputationRequestDto(5))
                .retrieveFlux(ComputationResponseDto.class)
                .doOnNext(System.out::println)
                .take(3);

        StepVerifier.create(mono)
                .expectNextCount(3)
                .verifyComplete();
    }

}
