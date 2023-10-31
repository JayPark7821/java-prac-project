package kr.jay.pilotproject.domain.post;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import kr.jay.pilotproject.common.jpa.BaseEntity;
import kr.jay.pilotproject.common.jpa.EntityChangeListeners;
import kr.jay.pilotproject.domain.users.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Post
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Entity
@Getter
@EntityListeners(value = EntityChangeListeners.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;


	public Post(final String title, final String content, final User user) {
		this.title = title;
		this.content = content;
		this.user = user;
	}
}
