package com.example.webflux.controller;

import java.util.Map;

import com.example.webflux.dto.Response;
import com.example.webflux.dto.MultiplyRequestDto;
import com.example.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/block/{input}")
    public Response findSquareBlock(@PathVariable int input){
        //No more reactive
        return this.mathService.findSquare(input).block();
    }

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input){
        // No more reactive
        //return this.mathService.findSquare(input).block();

        return this.mathService.findSquare(input)
                .defaultIfEmpty(new Response(-1));
    }

    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return this.mathService.multiplicationTable(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input) {
        return this.mathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> requestDtoMono,
                                   @RequestHeader Map<String, String> headers){
        System.out.println(headers);
        return this.mathService.multiply(requestDtoMono);
    }

}
