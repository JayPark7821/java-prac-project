package kr.jay.legacy.domain.campaign;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * LegacyCampaignTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
class LegacyCampaignTest {
    LegacyCampaign campaign = LegacyCampaign.of("name", 1L, 1000L);
    
    @Test
    void updateBudget() throws Exception{
        LocalDateTime before = LocalDateTime.now();
        campaign.updateBudget(100L);
        LocalDateTime after = LocalDateTime.now();
        assertAll(
            () -> assertThat(campaign.getBudget()).isEqualTo(100L),
            () -> assertThat(campaign.getUpdatedAt()).isAfter(before).isBefore(after)
        );
    }

}