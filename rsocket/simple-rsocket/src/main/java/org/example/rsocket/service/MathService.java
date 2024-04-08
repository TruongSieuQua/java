package org.example.rsocket.service;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import org.example.rsocket.dto.ChartResponseDto;
import org.example.rsocket.dto.RequestDto;
import org.example.rsocket.dto.ResponseDto;
import org.example.rsocket.util.ObjectUtil;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MathService implements RSocket{


//    @Override
//    public Mono<Void> fireAndForget(Payload payload){
//        try{
//            Thread.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("Receiving: " + payload.getDataUtf8()); //convert data to string
//        return Mono.empty();
//    }

    @Override
    public Mono<Void> fireAndForget(Payload payload){
        System.out.println("Receiving: " + ObjectUtil.toObject(payload, RequestDto.class)); //convert data to string
        return Mono.empty();
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        return Mono.fromSupplier(()->{
            RequestDto requestDto = ObjectUtil.toObject(payload, RequestDto.class);
            ResponseDto responseDto = new ResponseDto(requestDto.getInput(), requestDto.getInput()*requestDto.getInput());
            return ObjectUtil.toPayload(responseDto);
        });
    }

    // receive 1 request return 10 items
    @Override
    public Flux<Payload> requestStream(Payload payload){
        RequestDto requestDto = ObjectUtil.toObject(payload, RequestDto.class);
        return Flux.range(1, 10)
                .map(i -> i * requestDto.getInput())
                .map(i -> new ResponseDto(requestDto.getInput(), i))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(System.out::println)
                .doFinally(System.out::println)
                .map(ObjectUtil::toPayload);
    }

    // It will get payload, payload ... payload and return multiple items
    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads){
        return Flux.from(payloads)
                .map(p -> ObjectUtil.toObject(p, RequestDto.class))
                .map(RequestDto::getInput)
                .map(i -> new ChartResponseDto(i, (i*i) + 1))
                .map(ObjectUtil::toPayload);
    }
}
