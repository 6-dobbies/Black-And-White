package kr.pe.playdata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<Post> findByPostIdx(Long postIdx);

	List<Post> findByCategory(Board category);

	List<Post> findByWriter(Member writer);

	List<Post> findByTitleContaining(String title);

	List<Post> findByContentContaining(String content);

}
