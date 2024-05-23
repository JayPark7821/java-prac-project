package kr.jay.migration.domain.migration.user;

/**
 * MigrationUserAgreedEvent
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
public class MigrationUserAgreedEvent extends MigrationUserEvent{

    public MigrationUserAgreedEvent(MigrationUser migrationUser) {
        super(migrationUser);
    }
}

