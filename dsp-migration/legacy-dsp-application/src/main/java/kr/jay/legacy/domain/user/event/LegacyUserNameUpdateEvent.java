package kr.jay.legacy.domain.user.event;

import java.time.LocalDateTime;
import kr.jay.legacy.domain.user.LegacyUser;

/**
 * LegacyUserUpdateEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public class LegacyUserNameUpdateEvent extends LegacyUserEvent{

    public LegacyUserNameUpdateEvent(LegacyUser legacyUser) {
        super(legacyUser);
    }

    @Override
    public LocalDateTime occurredOn() {
        return legacyUser.getUpdatedAt();
    }
}
