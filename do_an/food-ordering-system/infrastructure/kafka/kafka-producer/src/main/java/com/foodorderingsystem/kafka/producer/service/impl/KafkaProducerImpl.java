package com.foodorderingsystem.kafka.producer.service.impl;

import com.foodorderingsystem.kafka.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topic, K key, V message, CompletableFuture<SendResult<K, V>> callback) {
        log.info("Sending message={} to topic={}", message, topic);
         CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topic, key, message);
        kafkaResultFuture.whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Failed to send message={} to topic={}", message, topic, exception);
                callback.completeExceptionally(exception);
            } else {
                log.info("Successfully sent message={} to topic={}", message, topic);
                callback.complete(result);
            }
        });
    }

    @PreDestroy
    public void close(){
        if(kafkaTemplate != null){
            log.info("Closing kafka producer");
            kafkaTemplate.destroy();
            log.info("Kafka producer closed");
        }
    }
}

