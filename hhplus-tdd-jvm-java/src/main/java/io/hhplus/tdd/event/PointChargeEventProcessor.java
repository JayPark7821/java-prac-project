package io.hhplus.tdd.event;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.event.topic.Event;
import io.hhplus.tdd.event.topic.EventStatus;
import io.hhplus.tdd.event.topic.EventTopic;
import io.hhplus.tdd.event.topic.PointChargeEvent;
import io.hhplus.tdd.point.UserPoint;
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
public class PointChargeEventProcessor implements EventProcessor {

    private static final EventTopic TOPIC = EventTopic.CHARGE;
    private final UserPointTable userPointTable;
    private final TransactionManager transactionManager;

    @Override
    public boolean isSupport(EventTopic topic) {
        return TOPIC == topic;
    }

    @Override
    public void process(Event event) {
        if (event instanceof PointChargeEvent pointChargeEvent) {
            String transactionId = pointChargeEvent.getTransactionId();
            log.info("process point charge event transactionId : {}", transactionId);
            UserPoint userPoint = userPointTable.selectById(pointChargeEvent.getUserId());

            try {
                userPointTable.insertOrUpdate(
                    pointChargeEvent.getUserId(),
                    userPoint.point() + pointChargeEvent.getAmount()
                );
                transactionManager.updateEventStatus(transactionId, event.getEventId(), EventStatus.DONE);

            } catch (Exception e) {
                log.error("error while processing point charge event", e);
                transactionManager.updateEventStatus(transactionId, event.getEventId(), EventStatus.FAIL);

            }
        }
    }
}
