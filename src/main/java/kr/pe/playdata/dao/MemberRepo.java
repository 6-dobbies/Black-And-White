package kr.pe.playdata.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.playdata.model.domain.Member;

public interface MemberRepo extends CrudRepository<Member, String>{
	
	
	List<Member> findMemberByOut(String out);
	List<Member> findMemberBynicknameContaining(String nickname);
	Member findMemberBynickname(String nickname);

}