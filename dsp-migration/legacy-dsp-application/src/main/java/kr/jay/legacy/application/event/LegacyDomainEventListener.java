package kr.jay.legacy.application.event;

import kr.jay.legacy.domain.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * LegacyDomainEventListener
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
@Component
@RequiredArgsConstructor
public class LegacyDomainEventListener {

    private final StreamBridge streamBridge;
    private static final String OUTPUT_BINDING = "legacy-rabbit-out";

    @TransactionalEventListener
    public void handleEvent(DomainEvent event) {
        streamBridge.send(OUTPUT_BINDING, LegacyDomainMessage.from(event));
    }

}
