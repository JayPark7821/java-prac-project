package kr.jay.migration.application.legacy.adgroup;

import jakarta.persistence.EntityNotFoundException;
import kr.jay.migration.application.LegacyConverter;
import kr.jay.migration.domain.legacy.adgroup.LegacyAdGroup;
import kr.jay.migration.domain.legacy.campaign.LegacyCampaign;
import kr.jay.migration.domain.legacy.campaign.LegacyCampaignRepository;
import kr.jay.migration.domain.recent.campaign.RecentCampaign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * LegacyAdGroupConverter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/21/24
 */
@Component
@RequiredArgsConstructor
public class LegacyAdGroupConverter implements LegacyConverter<LegacyAdGroup, RecentCampaign> {

    private final LegacyCampaignRepository legacyCampaignRepository;

    @Override
    public RecentCampaign convert(LegacyAdGroup legacyAdGroup) {
        LegacyCampaign campaign = legacyCampaignRepository.findById(legacyAdGroup.getCampaignId())
            .orElseThrow(EntityNotFoundException::new);
        return RecentCampaign.migrated(
            legacyAdGroup.getId(),
            campaign.getName() + "_" + legacyAdGroup.getName(),
            legacyAdGroup.getUserId(),
            campaign.getBudget(),
            legacyAdGroup.getLinkUrl(),
            legacyAdGroup.getCreatedAt(),
            legacyAdGroup.getUpdatedAt(),
            legacyAdGroup.getDeletedAt()
        );
    }
}
