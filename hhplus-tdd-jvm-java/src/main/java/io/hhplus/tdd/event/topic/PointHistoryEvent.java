package io.hhplus.tdd.event.topic;

import io.hhplus.tdd.point.TransactionType;
import java.util.UUID;
import lombok.Getter;

/**
 * PointHistoryEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/25/24
 */
@Getter
public class PointHistoryEvent extends Event {

    private final long userId;
    private final long amount;
    private final TransactionType transactionType;

    private PointHistoryEvent(
        String transactionId,
        long userId,
        long amount,
        TransactionType transactionType
    ) {
        super(transactionId, EventTopic.HISTORY);
        this.userId = userId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public static PointHistoryEvent ofCharge(String transactionId, long userId, long amount) {
        return new PointHistoryEvent(
            transactionId,
            userId,
            amount,
            TransactionType.CHARGE
        );
    }
}
