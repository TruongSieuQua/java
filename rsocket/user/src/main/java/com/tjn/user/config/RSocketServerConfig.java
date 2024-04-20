package com.tjn.user.config;

import com.tjn.user.dto.OperationType;
import io.rsocket.metadata.WellKnownMimeType;
import org.springframework.boot.rsocket.messaging.RSocketStrategiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RSocketServerConfig {

    @Bean
    public RSocketStrategiesCustomizer strategiesCustomizer(){
        MimeType mimeType = MimeTypeUtils.parseMimeType(WellKnownMimeType.APPLICATION_CBOR.getString());
        // convert metadata to object
        return c -> c.metadataExtractorRegistry(
                    // header "operation-type" is OperationType object and decode it by mimeType CBOR
                r -> r.metadataToExtract(mimeType, OperationType.class, "operation-type")
        );
    }

}
