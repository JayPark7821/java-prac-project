package kr.jay.legacy.application.keyword;

/**
 * LegacyKeywordCreateCommand
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
public record LegacyKeywordCreateCommand(String text, Long adGroupId, Long userId) {

}