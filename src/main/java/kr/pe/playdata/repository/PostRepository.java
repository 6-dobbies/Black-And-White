package kr.pe.playdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
