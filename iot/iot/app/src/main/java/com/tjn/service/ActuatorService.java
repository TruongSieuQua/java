package com.tjn.service;

import com.tjn.dto.SensorResponse;
import com.tjn.dto.SprinklerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class ActuatorService {

    private final Sinks.Many<SprinklerDto> actuatorStateSink = Sinks.many().replay().latest();

    private final SprinklerJsonProducer sprinklerProducer;

    @RabbitListener(queues = {"${rabbitmq.queue.actuatorState.name}"})
    private  void consumeSprinklerMessage(SprinklerDto res){
        actuatorStateSink.tryEmitNext(res);
    }

    public Flux<SprinklerDto> sprinklerStream(){
        return actuatorStateSink.asFlux();
    }

    public Mono<SprinklerDto> updateSprinkler(Integer id, SprinklerDto dto){
        var spinklerDto = new SprinklerDto(id, dto.forestName(), dto.state(), dto.cutOffThreshold(), dto.threshold());
        sprinklerProducer.sendJsonMessage(spinklerDto);
        return Mono.just(spinklerDto);
    }
}
