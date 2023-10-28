package kr.jay.pilotprojcet.common.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.PostPersist;
import kr.jay.pilotprojcet.common.jpa.audittrail.AuditLog;
import kr.jay.pilotprojcet.common.utils.ApplicationContextProvider;
import kr.jay.pilotprojcet.infrastructure.audit.AuditLogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EntityChangeListeners
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Slf4j
@RequiredArgsConstructor
public class EntityChangeListeners {

	@PostPersist
	public void saveChangeLog(BaseEntity entity) {
		log.info("entity changed = {}" , entity.toString());

		final AuditLogJpaRepository auditLogJpaRepository =
			ApplicationContextProvider.getApplicationContext().getBean(AuditLogJpaRepository.class);

		final ObjectMapper objectMapper =
			ApplicationContextProvider.getApplicationContext().getBean(ObjectMapper.class);

		try {
			auditLogJpaRepository.save(new AuditLog(objectMapper.writeValueAsString(entity)));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
