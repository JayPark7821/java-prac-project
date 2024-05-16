package kr.jay.legacy.application.event;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import kr.jay.legacy.application.user.LegacyUserCreateCommand;
import kr.jay.legacy.application.user.LegacyUserResult;
import kr.jay.legacy.application.user.LegacyUserService;
import kr.jay.legacy.domain.user.event.LegacyUserCreateEvent;
import kr.jay.legacy.domain.user.event.LegacyUserDeleteEvent;
import kr.jay.legacy.domain.user.event.LegacyUserEvent;
import kr.jay.legacy.domain.user.event.LegacyUserNameUpdateEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * LegacyDomainEventListenerTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */

@SpringBootTest
class LegacyDomainEventListenerTest {

    @Autowired
    LegacyUserService service;

    @MockBean
    LegacyDomainEventListener listener;

    @Test
    void userEvents() {
        LegacyUserResult user = service.create(new LegacyUserCreateCommand("user"));
        service.updateName(user.id(), "new user");
        service.delete(user.id());

        assertAll(
            () -> verify(listener, times(3)).handleEvent(any(DomainEvent.class)),
            () -> verify(listener, times(3)).handleEvent(any(LegacyUserEvent.class)),
            () -> verify(listener, times(1)).handleEvent(any(LegacyUserCreateEvent.class)),
            () -> verify(listener, times(1)).handleEvent(any(LegacyUserDeleteEvent.class)),
            () -> verify(listener, times(1)).handleEvent(any(LegacyUserNameUpdateEvent.class))
        );
    }
}