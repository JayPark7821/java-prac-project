package kr.jay.legacy.application.event;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import kr.jay.legacy.application.adgroup.LegacyAdGroupCreateCommand;
import kr.jay.legacy.application.adgroup.LegacyAdGroupResult;
import kr.jay.legacy.application.adgroup.LegacyAdGroupService;
import kr.jay.legacy.application.campaign.LegacyCampaignCreateCommand;
import kr.jay.legacy.application.campaign.LegacyCampaignResult;
import kr.jay.legacy.application.campaign.LegacyCampaignService;
import kr.jay.legacy.application.keyword.LegacyKeywordCreateCommand;
import kr.jay.legacy.application.keyword.LegacyKeywordResult;
import kr.jay.legacy.application.keyword.LegacyKeywordService;
import kr.jay.legacy.application.user.LegacyUserCreateCommand;
import kr.jay.legacy.application.user.LegacyUserResult;
import kr.jay.legacy.application.user.LegacyUserService;
import kr.jay.legacy.domain.DomainEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupCreatedEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupDeletedEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupLinkUrlUpdatedEvent;
import kr.jay.legacy.domain.adgroup.event.LegacyAdGroupNameUpdatedEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignBudgetUpdatedEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignCreatedEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignDeletedEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignEvent;
import kr.jay.legacy.domain.campaign.event.LegacyCampaignNameUpdatedEvent;
import kr.jay.legacy.domain.keyword.event.LegacyKeywordCreatedEvent;
import kr.jay.legacy.domain.keyword.event.LegacyKeywordDeletedEvent;
import kr.jay.legacy.domain.keyword.event.LegacyKeywordEvent;
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
    LegacyKeywordService keywordService;
    @Autowired
    LegacyAdGroupService adGroupService;
    @Autowired
    LegacyCampaignService campaignService;
    @Autowired
    LegacyUserService userService;
    @MockBean
    LegacyDomainEventListener eventListener;

    @Test
    void userEvents() {
        LegacyUserCreateCommand command = new LegacyUserCreateCommand("사용자");

        LegacyUserResult result = userService.create(command);
        userService.updateName(result.id(), "사용자1");
        userService.delete(result.id());

        assertAll(
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserCreateEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserNameUpdateEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserDeleteEvent.class)),
            () -> verify(eventListener, times(3)).handleEvent(any(LegacyUserEvent.class)),
            () -> verify(eventListener, times(3)).handleEvent(any(DomainEvent.class)));
    }

    @Test
    void campaignEvents() {
        LegacyCampaignCreateCommand command = new LegacyCampaignCreateCommand("캠페인", 1L, 200L);

        LegacyCampaignResult result = campaignService.create(command);
        campaignService.updateName(result.id(), "캠페인1");
        campaignService.updateBudget(result.id(), 1000L);
        campaignService.delete(result.id());

        assertAll(
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignCreatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(
                any(LegacyCampaignNameUpdatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(
                any(LegacyCampaignBudgetUpdatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignDeletedEvent.class)),
            () -> verify(eventListener, times(4)).handleEvent(any(LegacyCampaignEvent.class)),
            () -> verify(eventListener, times(4)).handleEvent(any(DomainEvent.class)));
    }

    @Test
    void adGroupEvents() {
        LegacyAdGroupCreateCommand command = new LegacyAdGroupCreateCommand("광고그룹", 1L, 1L,
            "https://www.fastcampus.com");

        LegacyAdGroupResult result = adGroupService.create(command);
        adGroupService.updateName(result.id(), "광고그룹1");
        adGroupService.updateLinkUrl(result.id(), "https://www.fastcampus1.com");
        adGroupService.delete(result.id());

        assertAll(
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupCreatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupNameUpdatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(
                any(LegacyAdGroupLinkUrlUpdatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupDeletedEvent.class)),
            () -> verify(eventListener, times(4)).handleEvent(any(LegacyAdGroupEvent.class)),
            () -> verify(eventListener, times(4)).handleEvent(any(DomainEvent.class)));
    }

    @Test
    void keywordEvents() {
        LegacyKeywordCreateCommand command = new LegacyKeywordCreateCommand("keyword", 1L, 1L);

        LegacyKeywordResult result = keywordService.create(command);
        keywordService.delete(result.id());

        assertAll(
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyKeywordCreatedEvent.class)),
            () -> verify(eventListener, times(1)).handleEvent(any(LegacyKeywordDeletedEvent.class)),
            () -> verify(eventListener, times(2)).handleEvent(any(LegacyKeywordEvent.class)),
            () -> verify(eventListener, times(2)).handleEvent(any(DomainEvent.class)));
    }

}