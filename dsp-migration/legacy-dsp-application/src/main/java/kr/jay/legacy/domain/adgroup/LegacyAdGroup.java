package kr.jay.legacy.domain.adgroup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupCreatedEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupDeletedEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupLinkUrlUpdatedEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupNameUpdatedEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignCreatedEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignNameUpdatedEvent;
import kr.jay.legacy.domain.user.LegacyUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * LegacyCampaign
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
@Entity
@NoArgsConstructor
@Getter
public class LegacyAdGroup extends AbstractAggregateRoot<LegacyAdGroup> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long userId;
    private Long campaignId;
    private String linkUrl;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private LegacyAdGroup(
        String name,
        Long userId,
        Long campaignId,
        String linkUrl,
        LocalDateTime createdAt
    ) {
        this.userId = userId;
        this.campaignId = campaignId;
        this.linkUrl = linkUrl;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
        registerEvent(new LegacyAdGroupCreatedEvent(this));
    }

    public static LegacyAdGroup of(
        String name,
        Long userId,
        Long campaignId,
        String linkUrl
    ) {
        return new LegacyAdGroup(name, userId, campaignId, linkUrl, LocalDateTime.now());
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        registerEvent(new LegacyAdGroupDeletedEvent(this));
    }

    public void updateName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new LegacyAdGroupNameUpdatedEvent(this));
    }

    public void updateLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        this.updatedAt = LocalDateTime.now();
        registerEvent(new LegacyAdGroupLinkUrlUpdatedEvent(this));
    }
}
