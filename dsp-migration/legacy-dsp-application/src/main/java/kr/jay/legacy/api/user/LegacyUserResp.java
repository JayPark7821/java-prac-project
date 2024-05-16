package kr.jay.legacy.api.user;

import java.time.LocalDateTime;
import kr.jay.legacy.application.user.LegacyUserResult;

public record LegacyUserResp(Long id, String name, LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             LocalDateTime deletedAt) {

  public static LegacyUserResp from(LegacyUserResult user) {
    return new LegacyUserResp(user.id(), user.name(), user.createdAt(),
        user.updatedAt(), user.deletedAt());
  }
}
