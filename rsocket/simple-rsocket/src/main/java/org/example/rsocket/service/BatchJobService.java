package org.example.rsocket.service;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import org.example.rsocket.dto.RequestDto;
import org.example.rsocket.dto.ResponseDto;
import org.example.rsocket.util.ObjectUtil;
import reactor.core.publisher.Mono;

import java.time.Duration;

// Server socket
public class BatchJobService implements RSocket {

    // Client side socket
    private RSocket rSocket;

    public BatchJobService(RSocket rSocket){this.rSocket = rSocket;}

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        RequestDto requestDto = ObjectUtil.toObject(payload, RequestDto.class);
        System.out.println("Received: " + requestDto);
        Mono.just(requestDto)
                .delayElement(Duration.ofSeconds(10))
                .doOnNext(i -> System.out.println("emitting"))
                .flatMap(this::findCube)
                .subscribe();

        return Mono.empty();
    }

    private Mono<Void> findCube(RequestDto requestDto){
        int input = requestDto.getInput();
        int output = input*input*input;
        ResponseDto responseDto = new ResponseDto(input, output);
        Payload payload = ObjectUtil.toPayload(responseDto);
        // Invoking fireAndForget method of client
        return this.rSocket.fireAndForget(payload);
    }
}
