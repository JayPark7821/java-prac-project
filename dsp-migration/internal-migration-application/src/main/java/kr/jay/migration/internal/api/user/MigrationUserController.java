package kr.jay.migration.internal.api.user;

import kr.jay.migration.application.user.MigrationUserResult;
import kr.jay.migration.application.user.MigrationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MigrationUserController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/migration/v1/user")
public class MigrationUserController {

    private final MigrationUserService migrationUserService;

    @PostMapping("/{userId}")
    public MigrationUserResp agree(@PathVariable("userId") Long userId) {
        MigrationUserResult result = migrationUserService.agree(userId);
        return new MigrationUserResp(
            result.id(),
            result.status(),
            result.agreedAt(),
            result.updatedAt()
        );
    }

    @GetMapping("/{userId}")
    public MigrationUserResp find(@PathVariable("userId") Long userId) {
        MigrationUserResult result = migrationUserService.findById(userId);
        return new MigrationUserResp(
            result.id(),
            result.status(),
            result.agreedAt(),
            result.updatedAt()
        );
    }
}
