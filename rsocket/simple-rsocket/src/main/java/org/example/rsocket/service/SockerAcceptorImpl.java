package org.example.rsocket.service;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import reactor.core.publisher.Mono;

public class SockerAcceptorImpl implements SocketAcceptor {

    // Accept connection
    // Run one time only before first request
    // non-blocking when receiving request
    // But blocking when handle request cause one MathService created
    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
        // print one time only
        System.out.println("class:SockerAcceptorImpl-method:accept");
        // socket acceptor contains socket
        // Call object to handle payload then return response
        return Mono.just(new MathService());
    }
}
