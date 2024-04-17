package com.springrsocket.controller;

import com.springrsocket.dto.Response;
import com.springrsocket.dto.error.ErrorEvent;
import com.springrsocket.dto.error.StatusCode;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@MessageMapping("math.validation")
public class InputValidationController {

//    @MessageMapping("double.{input}")
//    public Mono<Integer> doubleIt(@DestinationVariable int input){
//        if(input < 31) return Mono.just(input*2);
//        else return Mono.error(new IllegalArgumentException("can not be > 30"));
//    }

    @MessageMapping("double.{input}")
    public Mono<Integer> doubleIt(@DestinationVariable int input){
      return Mono.just(input)
              .filter(i -> i < 31)
              .map(i -> i*2)
              //.defaultIfEmpty(Integer.MIN_VALUE);
              .switchIfEmpty(Mono.error(new IllegalArgumentException("can't > 30")));
    }

    @MessageMapping("double.response.{input}")
    public Mono<Response<Integer>> doubleResponse(@DestinationVariable int input){
        return Mono.just(input)
                .filter(i -> i < 31)
                .map(i -> i*2)
                .map(Response::with)
                //.defaultIfEmpty(Integer.MIN_VALUE);
                .defaultIfEmpty(Response.with(new ErrorEvent(StatusCode.EC001)));
    }

    // @Controller Advise doesn't work. This method will handle Exception.class is thrown by this controller
    @MessageExceptionHandler(Exception.class)
    public Mono<Integer> handleException(Exception exception){
        return Mono.just(-1);
    }
}
