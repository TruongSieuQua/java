package com.example.webflux;

import com.example.webflux.dto.Response;
import reactor.core.publisher.Mono;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec01GetSingleResponseTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest(){
        Response response = this.webClient
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        System.out.println(response);
    }

    @Test
    public void setpVerifierTest(){

        // Buiding pipeline
        Mono<Response> responseMono = this.webClient
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(Response.class);

        // Check pipeline onNext element step
        StepVerifier.create(responseMono)
                .expectNextMatches(r -> r.getOutput() == 25)
                .verifyComplete();

    }


}
