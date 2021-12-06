package kr.pe.playdata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findByMemberIdx(Long memberIdx);
	Optional<Member> findByMemberId(String memberId);
	Optional<Member> findByNickname(String nickname);
	List<Member> findByNicknameContaining(String nickname);
	Optional<Member> findBydel(int del);

}