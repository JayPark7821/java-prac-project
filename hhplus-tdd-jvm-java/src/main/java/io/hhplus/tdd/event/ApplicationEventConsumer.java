package io.hhplus.tdd.event;

import io.hhplus.tdd.event.queue.ApplicationEventQueue;
import io.hhplus.tdd.event.topic.Event;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ApplicationEventConsumer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationEventConsumer {

    private final ApplicationEventQueue applicationEventQueue;
    private final List<EventProcessor> eventProcessors;

    @Scheduled(fixedRate = 1)
    public void onEvent() {
        Event event = applicationEventQueue.pop();

        if (event == null) {
            return;
        }

        eventProcessors.stream()
            .filter(listener -> listener.isSupport(event.getTopic()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No listener found for event: " + event))
            .process(event);
    }

}
