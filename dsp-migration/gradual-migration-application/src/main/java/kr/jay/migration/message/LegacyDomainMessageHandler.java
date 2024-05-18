package kr.jay.migration.message;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * LegacyDomainMessageHandler
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/18/24
 */
@Slf4j
@Component
public class LegacyDomainMessageHandler {

    @Bean
    public Consumer<LegacyDomainMessage> legacyConsumer(){
        return message -> log.info("Received message: {}", message);
    }

}
