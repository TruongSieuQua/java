package org.example.rsocket.client;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import org.example.rsocket.dto.ResponseDto;
import org.example.rsocket.util.ObjectUtil;
import reactor.core.publisher.Mono;

public class CallbackService implements RSocket {

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        System.out.println("Client received: " + ObjectUtil.toObject(payload, ResponseDto.class));

        return Mono.empty();
    }
}
