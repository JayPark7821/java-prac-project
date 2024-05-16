package kr.jay.legacy.application.campaign;

/**
 * LegacyCampaignCreateCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyCampaignCreateCommand(String name, Long userId, Long budget) {

}