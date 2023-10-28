package kr.jay.pilotprojcet.common.jpa;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

/**
 * BaseEntity
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {

	@CreatedDate
	@Comment("생성일시")
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(updatable = false)
	private String createdBy;

	@Comment("최종 수정일시")
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@LastModifiedBy
	private String updatedBy;
}
