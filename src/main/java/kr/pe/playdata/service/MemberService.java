package kr.pe.playdata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 회원 한 명 찾기 - nickname
//	@Transactional(readOnly = true)
//    public ResponseDTO.MemberResponse findByNickname(String nickname) {
//        Member entity = memberRepository.findByNickname(nickname)
//        		.orElseThrow(() -> new IllegalArgumentException("Member with nickname: " + nickname + " is not valid"));
//
//        return new ResponseDTO.MemberResponse(entity);
//    }

}
