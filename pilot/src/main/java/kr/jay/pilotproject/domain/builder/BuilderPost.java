package kr.jay.pilotproject.domain.builder;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.jay.pilotproject.common.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

/**
 * Post
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Entity
@Getter
// @EntityListeners(value = EntityChangeListeners.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
@Table(name = "post")
public class BuilderPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private BuilderUser builderUser;


    public BuilderPost(final String title, final String content, final BuilderUser builderUser) {
        this.title = title;
        this.content = content;
        this.builderUser = builderUser;
    }
}
