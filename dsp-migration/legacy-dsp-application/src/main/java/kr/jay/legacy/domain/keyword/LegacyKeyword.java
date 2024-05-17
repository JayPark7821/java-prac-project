package kr.jay.legacy.domain.keyword;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import kr.jay.legacy.domain.keyword.event.LegacyKeywordCreatedEvent;
import kr.jay.legacy.domain.keyword.event.LegacyKeywordDeletedEvent;
import kr.jay.legacy.domain.user.LegacyUser;
import kr.jay.legacy.domain.user.event.LegacyUserCreateEvent;
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
public class LegacyKeyword extends AbstractAggregateRoot<LegacyKeyword> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Long adGroupId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    private LegacyKeyword(
        String text,
        Long adGroupId,
        Long userId,
        LocalDateTime createdAt
    ) {
        this.text = text;
        this.adGroupId = adGroupId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyKeywordCreatedEvent(this));
    }

    public static LegacyKeyword of(String name, Long adGroupId, Long userId) {
        return new LegacyKeyword(name, adGroupId, userId, LocalDateTime.now());
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        registerEvent(new LegacyKeywordDeletedEvent(this));
    }
}
