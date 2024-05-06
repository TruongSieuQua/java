package com.tjn.reactivekafka.sec05;

import com.tjn.reactivekafka.sec05.KafkaConsumer;

/*
 * Test if each consumer in demo-group-123 get data from 1 partition in a minimum
 */
public class KafkaConsumerGroup {

    /*
     *  If consumer 2 is shutdown
     */

    private static class Consumer1{
        public static void main(String[] args) {
            // pass instanceId for instance in group demo-group-123
            KafkaConsumer.start("1");
            // get partitions 1, 2
        }
    }

    private static class Consumer2{
        public static void main(String[] args) {
            // pass instanceId for instance in group demo-group-123
            KafkaConsumer.start("2");
        }
    }

    private static class Consumer3{
        public static void main(String[] args) {
            // pass instanceId for instance in group demo-group-123
            KafkaConsumer.start("3");
            // get partitions 3
        }
    }

}
