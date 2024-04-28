# Create console producer with key separator and parse key
kafka-console-producer.sh \
    --bootstrap-server localhost:9092 \
    --topic hello-world \
    --property key.separator=: \
    --property parse.key=true

#Create console comsumer with a group
kafka-console-comsumer.sh \
    --bootstrap-server localhost:9092 \
    --topic hello-world \
    --property print.offset=true \
    --property print.key=true \
    --group is

kafka-console-comsumer.sh \
    --bootstrap-server localhost:9092 \
    --topic hello-world \
    --property print.offset=true \
    --property print.key=true \
    --group is

# Test the consumer group
1:a
2:b
3:c
4:d
5:e
2:sadasd
5:dasd
4:sadsw
3:asdasd

# List all the consumer groups
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
