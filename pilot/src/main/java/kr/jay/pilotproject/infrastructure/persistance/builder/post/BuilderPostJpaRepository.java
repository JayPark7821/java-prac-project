package kr.jay.pilotproject.infrastructure.persistance.builder.post;

import kr.jay.pilotproject.domain.builder.BuilderPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuilderPostJpaRepository extends JpaRepository<BuilderPost, Long> {

}