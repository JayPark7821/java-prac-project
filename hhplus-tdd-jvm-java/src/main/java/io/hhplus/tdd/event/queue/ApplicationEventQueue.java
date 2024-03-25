package io.hhplus.tdd.event.queue;

import io.hhplus.tdd.event.topic.Event;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * EventQueue
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Slf4j
@Component
public class ApplicationEventQueue {

    private static final HashMap<Long, Event> eventQueue = new HashMap<>();
    private static final AtomicLong id = new AtomicLong(0);

    public void publish(Event event) {
        log.info("publish event {} , {}", event.getTopic().name(), event.getEventId());
        eventQueue.put(id.incrementAndGet(), event);
    }

    public Event pop() {
        if (!eventQueue.isEmpty()) {
            return eventQueue.remove(eventQueue.keySet().iterator().next());
        } else {
            return null;
        }
    }
}
