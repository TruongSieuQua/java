package com.tjn.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.sensorTemperature.name}")
    private String sensorTemperatureQueue;

    @Value("${rabbitmq.queue.actuatorState.name}")
    private String actuatorStateQueue;

    @Value("${rabbitmq.routing.sensorTemperature.key}")
    private String sensorTemperatureRoutingKey;

    @Value("${rabbitmq.routing.actuatorState.key}")
    private String actuatorStateRoutingKey;

    @Bean
    public Queue sensorTemperatureQueue() {
        return new Queue(sensorTemperatureQueue);
    }

    @Bean
    public Queue actuatorStateQueue() {
        return new Queue(actuatorStateQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding sensorTemperatureBinding() {
        return BindingBuilder
                .bind(sensorTemperatureQueue())
                .to(exchange())
                .with(sensorTemperatureRoutingKey);
    }

    @Bean
    public Binding actuatorStateBinding() {
        return BindingBuilder
                .bind(actuatorStateQueue())
                .to(exchange())
                .with(actuatorStateRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

