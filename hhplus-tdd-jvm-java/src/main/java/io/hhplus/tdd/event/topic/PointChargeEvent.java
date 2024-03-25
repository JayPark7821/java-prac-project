package io.hhplus.tdd.event.topic;

import java.util.UUID;
import lombok.Getter;

/**
 * PointChargeEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Getter
public class PointChargeEvent extends Event {

    private final long userId;
    private final long amount;

    public PointChargeEvent(
        final String transactionId,
        final long userId,
        final long amount
    ) {
        super(transactionId, EventTopic.CHARGE);
        this.userId = userId;
        this.amount = amount;
    }
}
