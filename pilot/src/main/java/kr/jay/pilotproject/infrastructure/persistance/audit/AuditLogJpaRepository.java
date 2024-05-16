package kr.jay.pilotproject.infrastructure.persistance.audit;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.pilotproject.common.jpa.audit.AuditLog;

/**
 * EntityChangeLogJpaRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */
public interface AuditLogJpaRepository extends JpaRepository<AuditLog, Long> {
}
