package kr.jay.legacy.domain.adgroup;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class LegacyAdGroup {

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
    }

    public void updateName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
        this.updatedAt = LocalDateTime.now();
    }
}
