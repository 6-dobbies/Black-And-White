package kr.pe.playdata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<Post> findByPostIdx(Long postIdx);
	Optional<Post> findByCategory(Board category);
	Optional<Post> findByWriter(Member writer);
	Optional<Post> findByTitleContaining(String title);
	Optional<Post> findByContentContaining(String content);
	
}
