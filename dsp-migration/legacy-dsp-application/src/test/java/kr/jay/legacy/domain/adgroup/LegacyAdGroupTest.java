package kr.jay.legacy.domain.adgroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import kr.jay.legacy.domain.campaign.LegacyCampaign;
import org.junit.jupiter.api.Test;

/**
 * LegacyAdGroupTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
class LegacyAdGroupTest {
    LegacyAdGroup adGroup = LegacyAdGroup.of("name", 1L, 1000L, "linkUrl");

    @Test
    void updateBudget() throws Exception{
        LocalDateTime before = LocalDateTime.now();
        adGroup.updateLinkUrl("update url");
        LocalDateTime after = LocalDateTime.now();
        assertAll(
            () -> assertThat(adGroup.getLinkUrl()).isEqualTo("update url"),
            () -> assertThat(adGroup.getUpdatedAt()).isAfter(before).isBefore(after)
        );
    }
}