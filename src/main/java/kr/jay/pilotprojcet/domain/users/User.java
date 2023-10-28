package kr.jay.pilotprojcet.domain.users;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.jay.pilotprojcet.common.jpa.BaseEntity;
import kr.jay.pilotprojcet.common.jpa.EntityChangeListeners;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Users
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Getter
@Entity
@EntityListeners(value = {AuditingEntityListener.class, EntityChangeListeners.class})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public User(final String name) {
		this.name = name;
	}
}
