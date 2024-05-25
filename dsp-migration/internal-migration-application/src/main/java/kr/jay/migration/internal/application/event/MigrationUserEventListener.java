package kr.jay.migration.internal.application.event;

import java.util.Map;
import kr.jay.migration.domain.migration.user.MigrationUserAgreedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * MigrationUserEventListener
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/26/24
 */
@Component
@RequiredArgsConstructor
public class MigrationUserEventListener {

    private static final String OUTPUT_BINDING = "migration-user-out";
    private final StreamBridge streamBridge;

    @TransactionalEventListener
    public void handleAgreedEvent(MigrationUserAgreedEvent event) {
        streamBridge.send(
            OUTPUT_BINDING,
            MessageBuilder.createMessage(
                MigrationUserMessage.from(event),
                new MessageHeaders(Map.of("partitionKey", event.getUserId()))
            ));
    }

}
