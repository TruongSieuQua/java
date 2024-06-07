package com.tjn.service;

import com.tjn.dto.SprinklerDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprinklerJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.actuatorState.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(SprinklerJsonProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(SprinklerDto sprinklerDto) {
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, sprinklerDto);
        LOGGER.info(String.format("Json message sent -> %s", sprinklerDto.toString()));
    }
}
