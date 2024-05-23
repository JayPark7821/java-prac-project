package kr.jay.migration.domain.migration.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * MigrationUser
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/23/24
 */
@Entity
@NoArgsConstructor
@Getter
public class MigrationUser extends AbstractAggregateRoot<MigrationUser> {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private MigrationUserStatus status;

    private LocalDateTime agreedAt;

    private LocalDateTime updatedAt;

    private MigrationUser(Long id, LocalDateTime agreedAt) {
        this.id = id;
        this.status = MigrationUserStatus.AGREED;
        this.agreedAt = agreedAt;
        this.updatedAt = agreedAt;
        registerEvent(new MigrationUserAgreedEvent(this));
    }

    public static MigrationUser agreed(Long id){
        return new MigrationUser(id, LocalDateTime.now());
    }
}
