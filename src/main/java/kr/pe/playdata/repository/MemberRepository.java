package kr.pe.playdata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findByMemberIdx(Long memberIdx);
	Optional<Member> findByNickname(String nickname);
	Optional<Member> findByNicknameContaining(String nickname);
	Optional<Member> findBydel(int del);

}
