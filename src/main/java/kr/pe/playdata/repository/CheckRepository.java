package kr.pe.playdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.Member;

public interface CheckRepository extends CrudRepository<Member, Long> {

	List<Member> findByMemberId(String memberId);

	List<Member> findByNickname(String nickname);
	
	List<Member> findByEmail(String email);
	
}
