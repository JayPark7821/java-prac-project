package kr.jay.legacy.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import kr.jay.legacy.domain.user.event.LegacyUserCreateEvent;
import kr.jay.legacy.domain.user.event.LegacyUserDeleteEvent;
import kr.jay.legacy.domain.user.event.LegacyUserNameUpdateEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * LegacyUser
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
@Entity
@NoArgsConstructor
@Getter
public class LegacyUser extends AbstractAggregateRoot<LegacyUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private LegacyUser(
        String name,
        LocalDateTime createdAt
    ) {
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyUserCreateEvent(this));
    }

    public static LegacyUser of(String name) {
        return new LegacyUser(name, LocalDateTime.now());
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        registerEvent(new LegacyUserDeleteEvent(this));
    }

    public void updateName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new LegacyUserNameUpdateEvent(this));
    }
}
