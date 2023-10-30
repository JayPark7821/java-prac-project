package kr.jay.pilotproject.infrastructure.persistance.post;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.pilotproject.domain.post.Post;

interface PostJpaRepository extends JpaRepository<Post, Long> {

}