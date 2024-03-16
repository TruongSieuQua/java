package com.example.webflux.webclient;

import com.example.webflux.dto.MultiplyRequestDto;
import com.example.webflux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec03PostRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void postTest(){
        Mono<Response> responseMono = webClient.post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(5, 2))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                //.expectNextCount(1) // It will consume response so expectNextMatches will receive onComplete
                .expectNextMatches(r -> r.getOutput() == 10)
                .verifyComplete();
    }

    private MultiplyRequestDto buildRequestDto(int a, int b){
        MultiplyRequestDto dto = new MultiplyRequestDto();
        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }

}
