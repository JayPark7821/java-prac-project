package kr.jay.pilotproject.common.jpa;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.PostPersist;
import kr.jay.pilotproject.common.jpa.audit.AuditLog;
import kr.jay.pilotproject.common.utils.ApplicationContextProvider;
import kr.jay.pilotproject.infrastructure.persistance.audit.AuditLogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EntityChangeListeners
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Transactional
@Slf4j
public class EntityChangeListeners {

	@PostPersist // TODO : Transaction scope, 의존성 주입 부분 확인
	public void saveChangeLog(BaseEntity entity) {
		final AuditLogJpaRepository auditLogJpaRepository =
			ApplicationContextProvider.getApplicationContext().getBean(AuditLogJpaRepository.class);

		final ObjectMapper objectMapper =
			ApplicationContextProvider.getApplicationContext().getBean(ObjectMapper.class);

		try {
			final String changeHistory = objectMapper.writeValueAsString(entity);
			log.info("changeHistory : {}", changeHistory);
			auditLogJpaRepository.save(new AuditLog(changeHistory));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
