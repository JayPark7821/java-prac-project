package kr.jay.pilotproject.domain.builder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.jay.pilotproject.common.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

/**
 * Users
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Getter
@Entity
// @EntityListeners(value = EntityChangeListeners.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Audited
public class BuilderUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public BuilderUser(final String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
