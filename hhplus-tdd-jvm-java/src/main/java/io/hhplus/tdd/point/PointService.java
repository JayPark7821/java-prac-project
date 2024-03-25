package io.hhplus.tdd.point;

import io.hhplus.tdd.event.queue.ApplicationEventQueue;
import io.hhplus.tdd.event.topic.Event;
import io.hhplus.tdd.event.topic.PointChargeEvent;
import io.hhplus.tdd.event.topic.PointHistoryEvent;
import io.hhplus.tdd.transaction.TransactionManager;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * PointService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {

    private final ApplicationEventQueue applicationEventQueue;
    private final TransactionManager transactionManager;

    public UserPoint charge(long id, long amount) {
        log.info("charge started : id={}, amount={}", id, amount);

        transactionManager.executeEventsWithTransaction(() -> {
            String transactionId = UUID.randomUUID().toString();
            final PointChargeEvent chargeEvent = new PointChargeEvent(transactionId, id, amount);
            final PointHistoryEvent historyEvent = PointHistoryEvent.ofCharge(transactionId, id, amount);
            final List<Event> events = List.of(chargeEvent, historyEvent);
            events.forEach(applicationEventQueue::publish);
            return events;
        });

        return null;
    }
}
