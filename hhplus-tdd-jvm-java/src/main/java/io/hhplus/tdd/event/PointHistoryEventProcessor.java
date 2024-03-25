package io.hhplus.tdd.event;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.event.topic.Event;
import io.hhplus.tdd.event.topic.EventStatus;
import io.hhplus.tdd.event.topic.EventTopic;
import io.hhplus.tdd.event.topic.PointHistoryEvent;
import io.hhplus.tdd.transaction.TransactionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * PointChargeEventListener
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PointHistoryEventProcessor implements EventProcessor {

    private static final EventTopic TOPIC = EventTopic.HISTORY;
    private final PointHistoryTable pointHistoryTable;
    private final TransactionManager transactionManager;

    @Override
    public boolean isSupport(EventTopic topic) {
        return TOPIC == topic;
    }

    @Override
    public void process(Event event) {
        if (event instanceof PointHistoryEvent pointHistoryEvent) {
            String transactionId = pointHistoryEvent.getTransactionId();
            log.info("process point history event transactionId : {}", transactionId);

            try {
                pointHistoryTable.insert(
                    pointHistoryEvent.getUserId(),
                    pointHistoryEvent.getAmount(),
                    pointHistoryEvent.getTransactionType(),
                    pointHistoryEvent.getEventTime()
                );
                transactionManager.updateEventStatus(transactionId, event.getEventId(), EventStatus.DONE);

            } catch (Exception e) {
                log.error("error while processing point charge event", e);
                transactionManager.updateEventStatus(transactionId, event.getEventId(), EventStatus.FAIL);

            }
        }
    }
}
