package kr.pe.playdata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.playdata.model.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByMemberIdx(Long memberIdx);

	Member findByMemberId(String memberId);

	Member findByNickname(String nickname);
	
	Member findByEmail(String email);
	
	List<Member> findByNicknameContaining(String nickname);
	
	Optional<Member> findByEmail(String email);

	List<Member> findByDel(int del);
	
}
