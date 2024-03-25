package io.hhplus.tdd.transaction;

import static java.util.stream.Collectors.groupingBy;
import io.hhplus.tdd.event.queue.ApplicationEventQueue;
import io.hhplus.tdd.event.topic.Event;
import io.hhplus.tdd.event.topic.EventStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TransactionManager
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionManager {

    private final ApplicationEventQueue applicationEventQueue;
    private static final Map<String, TransactionEventStore> transactions = new HashMap<>();

    public void executeEventsWithTransaction(Supplier<List<Event>> supplier) {

        List<Event> events = supplier.get();

        Map<String, List<Event>> transactionGroup =
            events.stream().collect(groupingBy(Event::getTransactionId));

        transactionGroup.forEach((transactionId, eventList) -> {

            startTransaction(transactionId, events);
            TransactionEventStore transactionEventStore = transactions.get(transactionId);

            if (transactionEventStore.needToRollback()) {
                rollbackSucceedEvents(transactionEventStore, events);
            }
        });
    }

    private void rollbackSucceedEvents(TransactionEventStore transactionEventStore, List<Event> events) {
        List<String> compensateEventIds = transactionEventStore.getSucceedEventIds();
        compensateEventIds.forEach(eventId ->
            events.stream()
                .filter(event -> event.getEventId().equals(eventId))
                .forEach(event -> {
                    event.updateToCompensateEvent();
                    applicationEventQueue.publish(event);
                }));
    }

    private void startTransaction(String transactionId, List<Event> events) {
        try {
            TransactionEventStore transactionEventStore = TransactionEventStore.initialize(events);
            transactions.put(transactionId, transactionEventStore);
            transactionEventStore.startTransaction();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEventStatus(String transactionId, String eventId, EventStatus status) {
        TransactionEventStore eventStore = transactions.get(transactionId);
        eventStore.updateEventStatus(eventId, status);
    }


}
