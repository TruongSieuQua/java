package org.example.rsocket.service;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import reactor.core.publisher.Mono;

public class SockerAcceptorImpl implements SocketAcceptor {

    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
        System.out.println("class:SockerAcceptorImpl-method:accept");
        return Mono.just(new MathService());
    }
}
