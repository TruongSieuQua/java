package com.tjn.reactivekafka.sec03;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.time.Duration;
import java.util.Map;

/*
    goal: to demo back pressure using max in flight for a reactive producer.
    producer could be a confusing term & it depends on the context.
 */
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    // my pc: 50_000 messages/s (17s if InFlight = 10_000, 14s if InFlight=20_000)
    public static void main(String[] args) {

        var producerConfig = Map.<String, Object>of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );

        var options = SenderOptions.<String, String>create(producerConfig)
                // Rate limiter for Sender to Kafka
                // change limitRate to x <= 10_000 at once
                // If sending 1_000_000 => 100 pre-sending instead of sending 1_000_000 at once
                // After 75% sending (onNext has called) it will process produce next 75%
                .maxInFlight(10_000);

        var flux = Flux.range(1, 1_000_000)
                .map(KafkaProducer::createSenderRecord);


        var start = System.currentTimeMillis();
        var sender = KafkaSender.create(options);
        sender.send(flux)
                .doOnNext(r -> log.info("correlation id: {}", r.correlationMetadata()))
                .doOnComplete(() -> {
                    log.info("Total time taken: {} ms", (System.currentTimeMillis() - start));
                    sender.close();
                })
                .subscribe();
    }

    private static SenderRecord<String, String, String> createSenderRecord(Integer i){
        var headers = new RecordHeaders();
        headers.add("client-id", "some-client".getBytes());
        headers.add("tracing-id", "123".getBytes());

        var pr = new ProducerRecord<>("order-events", null, i.toString(), "order-"+i, headers);

        return SenderRecord.create(pr, pr.key());
    }
}
