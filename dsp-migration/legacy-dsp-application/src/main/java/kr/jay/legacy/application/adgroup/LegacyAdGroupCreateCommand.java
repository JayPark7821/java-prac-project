package kr.jay.legacy.application.adgroup;

/**
 * LegacyAdGroupCreateCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyAdGroupCreateCommand(
    String name,
    Long campaignId,
    Long userId,
    String linkUrl
) {}