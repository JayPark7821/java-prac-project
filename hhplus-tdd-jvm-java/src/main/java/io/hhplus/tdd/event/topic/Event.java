package io.hhplus.tdd.event.topic;

import java.util.UUID;
import lombok.Getter;

/**
 * Event
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */

@Getter
public abstract class Event {

    private final String transactionId;
    private final String eventId;
    private final EventTopic topic;
    private boolean compensateEvent;
    private final long eventTime;

    protected Event(String transactionId, EventTopic topic) {
        this.transactionId = transactionId;
        this.eventId = UUID.randomUUID().toString();
        this.topic = topic;
        this.eventTime = System.currentTimeMillis();
        this.compensateEvent = false;
    }

    public void updateToCompensateEvent() {
        this.compensateEvent = true;
    }
}
