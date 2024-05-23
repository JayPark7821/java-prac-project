package kr.jay.migration.domain.migration.user;

/**
 * MigrationUserEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
public class MigrationUserEvent {

    protected MigrationUser migrationUser;

    public MigrationUserEvent(MigrationUser migrationUser) {
        this.migrationUser = migrationUser;
    }

    public Long getUserId() {
        return migrationUser.getId();
    }

    public MigrationUserStatus getStatus() {
        return migrationUser.getStatus();
    }
}

