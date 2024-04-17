package org.example.rsocket;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.example.rsocket.dto.ChartResponseDto;
import org.example.rsocket.dto.RequestDto;
import org.example.rsocket.dto.ResponseDto;
import org.example.rsocket.util.ObjectUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // @BeforeAll, @AfterAll run one time when instance initial
public class Lec01RSocketTest {


    private RSocket rSocker;

    @BeforeAll
    public void setup(){
        // client socket ( object represent server socket ) by connect to server "localhost:6565"
        this.rSocker = RSocketConnector.create()
                .connect(TcpClientTransport.create("localhost", 6565))
                // block till it connect completely to server
                .block();
    }

//    @RepeatedTest(3)
//    public void fireAndForget(){
//        // client socket fire a payload to server
//        Payload payload = DefaultPayload.create("Hello World!");
//        Mono<Void> mono = this.rSocker.fireAndForget(payload);
//
//        // Mono nothing happens util you subscribe
//
//        StepVerifier
//                .create(mono) // will subscribe a mono
//                .verifyComplete();
//    }

    @Test
    public void fireAndForget(){
        // Create payload
        Payload payload = ObjectUtil.toPayload(new RequestDto(5));

        // call client socket fireAndForget
        Mono<Void> mono = this.rSocker.fireAndForget(payload);

        // Mono nothing happens util you subscribe
        StepVerifier
                .create(mono) // will subscribe a mono
                .verifyComplete();
    }

    @Test
    public void requestResponse(){
        // client socket fire a payload to server
        Payload payload = ObjectUtil.toPayload(new RequestDto(5));
        Mono<ResponseDto> mono = this.rSocker.requestResponse(payload)
                .map(p -> ObjectUtil.toObject(p, ResponseDto.class))
                .doOnNext(System.out::println);
        // Mono nothing happens util you subscribe
        StepVerifier
                .create(mono) // will subscribe a mono
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void requestStream(){
        // client socket fire a payload to server
        Payload payload = ObjectUtil.toPayload(new RequestDto(5));
        Flux<ResponseDto> flux = this.rSocker.requestStream(payload)
                .map(p -> ObjectUtil.toObject(p, ResponseDto.class))
                .doOnNext(System.out::println)
                .take(4);

        // Mono nothing happens util you subscribe
        StepVerifier
                .create(flux) // will subscribe a mono
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void requestChannel(){
        // create publisher
        Flux<Payload> payloads = Flux.range(-10, 21)
                .delayElements(Duration.ofMillis(500))
                .map(RequestDto::new)
                .map(ObjectUtil::toPayload);

        Flux<ChartResponseDto> flux = this.rSocker.requestChannel(payloads)
                .map(p -> ObjectUtil.toObject(p, ChartResponseDto.class))
                .doOnNext(System.out::println);

        // Mono nothing happens util you subscribe
        StepVerifier
                .create(flux) // will subscribe a mono
                .expectNextCount(21)
                .verifyComplete();
    }
}
