package kr.pe.playdata.service;

import java.util.List;
import java.util.stream.Collectors;

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
	
	// 회원 한 명 조회 - memberIdx
	@Transactional(readOnly = true)
	public ResponseDTO.MemberResponse findByMemberIdx(Long memberIdx) {
		Member entity = memberRepository.findByMemberIdx(memberIdx)
				.orElseThrow(() -> new IllegalArgumentException("Member with memberIdx: " + memberIdx + " is not valid"));
		
		return new ResponseDTO.MemberResponse(entity);
	}
	
	// 회원 한 명 조회 - nickname
	@Transactional(readOnly = true)
    public ResponseDTO.MemberResponse findByNickname(String nickname) {
        Member entity = memberRepository.findByNickname(nickname)
        		.orElseThrow(() -> new IllegalArgumentException("Member with nickname: " + nickname + " is not valid"));

        return new ResponseDTO.MemberResponse(entity);
    }
	
	// 회원 전체 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.MemberListResponse> findMemberAll() {
		return memberRepository.findAll().stream()
										 .map(ResponseDTO.MemberListResponse::new)
										 .collect(Collectors.toList());
	}

}
