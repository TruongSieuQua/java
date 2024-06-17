package tjn.ms.twittertokafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import tjn.ms.config.TwitterToKafkaServiceProperties;
import tjn.ms.twittertokafka.runner.StreamRunner;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(TwitterToKafkaServiceProperties.class)
@ComponentScan(basePackages = "tjn.ms")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final TwitterToKafkaServiceProperties twitterToKafkaServiceProperties;

    private final StreamRunner streamRunner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App starts...");
        LOG.info(Arrays.toString(twitterToKafkaServiceProperties.twitterKeywords().toArray(new String[] {})));
        streamRunner.start();
    }
}