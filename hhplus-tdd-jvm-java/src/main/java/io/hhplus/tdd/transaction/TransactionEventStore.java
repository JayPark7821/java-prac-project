package io.hhplus.tdd.transaction;

import io.hhplus.tdd.event.topic.Event;
import io.hhplus.tdd.event.topic.EventStatus;
import io.hhplus.tdd.event.topic.EventTopic;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;

@Getter
public class TransactionEventStore {

    private final CountDownLatch latch;
    private final List<TransactionEvent> transactionEvents;

    private TransactionEventStore(CountDownLatch latch, List<TransactionEvent> transactionEvents) {
        this.latch = latch;
        this.transactionEvents = transactionEvents;
    }

    public static TransactionEventStore initialize(List<Event> events) {
        final List<TransactionEvent> transactionEvents =
            events.stream()
                .map(event -> new TransactionEvent(event.getEventId()))
                .toList();
        return new TransactionEventStore(new CountDownLatch(events.size()), transactionEvents);
    }

    public void startTransaction() throws InterruptedException {
        this.latch.await();
    }

    public void updateEventStatus(String eventId, EventStatus status) {
        this.transactionEvents.stream()
            .filter(transactionEvent -> transactionEvent.getEventId().equals(eventId))
            .forEach(event -> event.updateStatus(status));

        this.latch.countDown();
    }

    public boolean needToRollback() {
        return this.transactionEvents.stream()
            .anyMatch(event -> event.getStatus() == EventStatus.FAIL);
    }

    public List<String> getSucceedEventIds() {
        return this.transactionEvents.stream()
            .filter(event -> event.getStatus() == EventStatus.DONE)
            .map(TransactionEvent::getEventId)
            .toList();
    }

    @Getter
    private static class TransactionEvent {
        private final String eventId;
        private EventStatus status;

        private TransactionEvent(String eventId) {
            this.eventId = eventId;
            this.status = EventStatus.READY;
        }

        private void updateStatus(EventStatus status) {
            this.status = status;
        }
    }
}

