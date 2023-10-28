package kr.jay.pilotprojcet.infrastructure.post;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.jay.pilotprojcet.domain.post.Post;

interface PostJpaRepository extends JpaRepository<Post, Long> {

}