package kr.jay.legacy.api.keyword;

import java.time.LocalDateTime;
import kr.jay.legacy.application.keyword.LegacyKeywordResult;

public record LegacyKeywordResp(Long id, String text, Long adGroupId, LocalDateTime createdAt,
                                LocalDateTime deletedAt) {

  public static LegacyKeywordResp from(LegacyKeywordResult keyword) {
    return new LegacyKeywordResp(keyword.id(), keyword.text(), keyword.adGroupId(),
        keyword.createdAt(), keyword.deletedAt());
  }
}
