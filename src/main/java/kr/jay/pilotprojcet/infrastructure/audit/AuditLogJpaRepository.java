package kr.jay.pilotprojcet.infrastructure.audit;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.pilotprojcet.common.jpa.audittrail.AuditLog;

/**
 * EntityChangeLogJpaRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/29/23
 */
public interface AuditLogJpaRepository extends JpaRepository<AuditLog, Long> {
}
