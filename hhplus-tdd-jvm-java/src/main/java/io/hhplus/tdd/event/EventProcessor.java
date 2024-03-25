package io.hhplus.tdd.event;

import io.hhplus.tdd.event.topic.Event;
import io.hhplus.tdd.event.topic.EventTopic;

/**
 * PointEventListener
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
public interface EventProcessor {

    boolean isSupport(EventTopic topic);

    void process(Event event);
}
