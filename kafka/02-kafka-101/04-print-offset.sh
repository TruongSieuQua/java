# create console producer
kafka-console-producer.sh \
    --bootstrap-server localhost:9092 \
    --topic hello-world \
    --property key.separator=: \
    --property parse.key=true

# create console consumer with a group
kafka-console-consumer.sh \
    --bootstrap-server localhost:9092 \
    --topic hello-world \
    --property print.offset=true \
    --property print.key=true \
    --group name

# list all the consumer groups
 kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list 

 # describe a consumer group (current off-set is the lastest messages consumed by consumer group in each partition)
 kafka-consumer-groups.sh \
    --bootstrap-server localhost:9092 \
    --group cg \
    --describe  

# connect consumer again to get newest item from current off-set to newest item of all partitions in the topic