package kr.jay.pilotproject.infrastructure.persistance.prod.post;

import kr.jay.pilotproject.domain.prod.ProdPost;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProdPostJpaRepository extends JpaRepository<ProdPost, Long> {

}