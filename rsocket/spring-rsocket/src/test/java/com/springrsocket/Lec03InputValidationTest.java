package com.springrsocket;

import com.springrsocket.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec03InputValidationTest extends BaseTest {

    @Test
    public void validationTest(){
        Mono<Integer> mono = requester.route("math.validation.double.50")
                .retrieveMono(Integer.class)
                .doOnNext(System.out::println)
                //.onErrorReturn(Integer.MIN_VALUE) // MessageHandler will handle exception
                ;

        StepVerifier.create(mono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void responseTest(){
        Mono<Response<Integer>> mono = requester.route("math.validation.double.response.50")
                .retrieveMono(new ParameterizedTypeReference<Response<Integer>>() {})
                .doOnNext(r -> {
                    if(r.hasError()){
                        System.out.println(r.getErrorResponse().getStatusCode().getDescription());
                    }else{
                        System.out.println(r.getSuccessResponse());
                    }
                });

        StepVerifier.create(mono)
                .expectNextCount(1)
                .verifyComplete();
    }

}
