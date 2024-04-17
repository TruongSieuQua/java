package org.example.rsocket.service;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import reactor.core.publisher.Mono;

public class SockerAcceptorImpl implements SocketAcceptor {

//    // Accept connection
//    // Run one time only before first request
//    // non-blocking when receiving request
//    // But blocking when handle request cause one MathService created
//    @Override
//    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
//        // print one time only
//        System.out.println("class:SockerAcceptorImpl-method:accept");
//        // socket acceptor contains socket
//        // Call object to handle payload then return response
//        return Mono.just(new MathService());
//    }

//    @Override
//    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
//        // print one time only
//        System.out.println("class:SockerAcceptorImpl-method:accept");
//        // socket acceptor contains socket
//        // Call object to handle payload then return response
//        return Mono.fromCallable(() -> new BatchJobService(rSocket));
//    }

//    @Override
//    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
//        // print one time only
//        System.out.println("class:SockerAcceptorImpl-method:accept");
//        // socket acceptor contains socket
//        // Call object to handle payload then return response
//        return Mono.fromCallable(FastProducerService::new);
//    }

    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
        System.out.println("class:SockerAcceptorImpl-method:accept");

        if(isValidClient(connectionSetupPayload.getDataUtf8())){
            return Mono.just(new MathService());
        }else{
            return Mono.just(new FreeService());
        }
    }

    private boolean isValidClient(String credentials){
        return "user:password".equals(credentials);
    }
}
