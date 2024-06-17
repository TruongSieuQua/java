package tjn.ms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public record TwitterToKafkaServiceProperties(
        List<String> twitterKeywords,
        Boolean enableMockTweets,
        Long mockSleepMs,
        Integer mockMinTweetLength,
        Integer mockMaxTweetLength
){}