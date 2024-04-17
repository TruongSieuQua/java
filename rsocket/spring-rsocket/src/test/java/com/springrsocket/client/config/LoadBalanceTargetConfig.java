package com.springrsocket.client.config;

import com.springrsocket.client.serviceregistry.RSocketServerInstance;
import com.springrsocket.client.serviceregistry.ServiceRegistryClient;
import io.rsocket.loadbalance.LoadbalanceTarget;
import io.rsocket.transport.ClientTransport;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

//@Configuration
public class LoadBalanceTargetConfig {

    @Autowired
    private ServiceRegistryClient registryClient;

    /*
        If you want to emit updated list of items periodically then you can change mono to flux
    */
//    @Bean
//    public Flux<List<LoadbalanceTarget>> targetsFlux(){
//        return Flux.from(targets());
//    }

//    private Mono<List<LoadbalanceTarget>> targets(){
//        return Mono.fromSupplier(() -> this.registryClient.getInstances()
//                .stream().map(server -> LoadbalanceTarget.from(key(server), transport(server)))
//                .collect(Collectors.toList()));
//    }
//
//    private String key(RSocketServerInstance instance){
//        return instance.getHost() + instance.getPort();
//    }
//
//    private ClientTransport transport(RSocketServerInstance instance){
//        return TcpClientTransport.create(instance.getHost(), instance.getPort());
//    }
}
