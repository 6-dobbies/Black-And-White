package kr.pe.playdata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findBynickname(String nickname);
	Optional<Member> findBynicknameContaining(String nickname);
	Optional<Member> findByOut(int out);

}
