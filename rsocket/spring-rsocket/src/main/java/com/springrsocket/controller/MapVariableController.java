package com.springrsocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@MessageMapping("math.service")
public class MapVariableController {

    @MessageMapping("print.{input}")
    public Mono<Void> print(@DestinationVariable int input){
        System.out.println("Received: " + input);
        return Mono.empty();
    }

}
