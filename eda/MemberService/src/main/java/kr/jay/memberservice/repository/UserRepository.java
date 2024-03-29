package kr.jay.memberservice.repository;

import java.util.Optional;
import kr.jay.memberservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/29/24
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginId(String loginId);

}
