package kr.jay.migration.domain.legacy.campaign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import kr.jay.migration.domain.legacy.DeletableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * LegacyCampaign
 *
 * @author jaypark
 * @version 1.0.0
 * @since 5/16/24
 */
@Entity
@NoArgsConstructor
@Getter
public class LegacyCampaign implements DeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long userId;
    private Long budget;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
