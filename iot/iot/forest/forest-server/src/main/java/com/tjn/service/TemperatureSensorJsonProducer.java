package com.tjn.service;

import com.tjn.dto.SensorResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureSensorJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.sensorTemperature.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureSensorJsonProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(SensorResponse sensorResponse) {
        LOGGER.info(String.format("Json message sent -> %s", sensorResponse.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, sensorResponse);
    }
}
