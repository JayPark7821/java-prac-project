package kr.jay.legacy.domain.campaign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
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
public class LegacyCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long userId;
    private Long budget;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private LegacyCampaign(
        String name,
        Long userId,
        Long budget,
        LocalDateTime createdAt
    ) {
        this.userId = userId;
        this.budget = budget;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.deletedAt = null;
    }

    public static LegacyCampaign of(
        String name,
        Long userId,
        Long budget
    ) {
        return new LegacyCampaign(name, userId, budget, LocalDateTime.now());
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void updateName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateBudget(Long budget) {
        this.budget = budget;
        this.updatedAt = LocalDateTime.now();
    }
}
