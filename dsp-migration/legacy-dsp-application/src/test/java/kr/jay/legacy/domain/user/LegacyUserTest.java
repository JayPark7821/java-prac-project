package kr.jay.legacy.domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * LegacyUserTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
class LegacyUserTest {

    LegacyUser user = LegacyUser.of("name");

    @Test
    void updateName() throws Exception {
        LocalDateTime before = LocalDateTime.now();
        user.updateName("newName");
        LocalDateTime after = LocalDateTime.now();
        assertAll(
            () -> assertThat(user.getName()).isEqualTo("newName"),
            () -> assertThat(user.getUpdatedAt()).isAfter(before).isBefore(after)
        );
    }

    @Test
    void delete() throws Exception {
        LocalDateTime before = LocalDateTime.now();
        user.delete();
        LocalDateTime after = LocalDateTime.now();

        assertThat(user.getDeletedAt())
            .isAfter(before)
            .isBefore(after);
    }
}