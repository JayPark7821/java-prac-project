package kr.jay.pilotproject.common.jpa.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * EntityChangeLog
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String changeHistory;

	public AuditLog(final String changeHistory) {
		this.changeHistory = changeHistory;
	}
}
