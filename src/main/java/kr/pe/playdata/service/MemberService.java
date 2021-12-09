package kr.pe.playdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.exception.CUserNotFoundException;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	@Autowired
	private final MemberRepository memberRepository;
	
	// 회원 조회 - memberIdx
	@Transactional(readOnly = true)
	public ResponseDTO.MemberResponse findByMemberIdx(Long memberIdx) {
		Member entity = memberRepository.findByMemberIdx(memberIdx)
										.orElseThrow(() -> new CUserNotFoundException("Member with memberIdx: " + memberIdx + " is not valid"));
		
		return new ResponseDTO.MemberResponse(entity);
	}
	
	// 회원 조회 - nickname
	@Transactional(readOnly = true)
    public ResponseDTO.MemberResponse findByNickname(String nickname) {
        Member entity = memberRepository.findByNickname(nickname)
        								.orElseThrow(() -> new CUserNotFoundException("Member with nickname: " + nickname + " is not valid"));

        return new ResponseDTO.MemberResponse(entity);
    }
	
	// 회원 조회 - nickname 일부
	@Transactional(readOnly = true)
	public List<ResponseDTO.MemberListResponse> findByNicknameContaining(String nickname) {
		return memberRepository.findByNicknameContaining(nickname)
							   .stream()
							   .map(ResponseDTO.MemberListResponse::new)
							   .collect(Collectors.toList());
	}
	
	// 회원 전체 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.MemberListResponse> findMemberAll() {
		return memberRepository.findAll()
							   .stream()
							   .map(ResponseDTO.MemberListResponse::new)
							   .collect(Collectors.toList());
	}
	
	// 회원 저장
	@Transactional
	public Long saveMember(Member member) {
        return memberRepository.save(member).getMemberIdx();
    }
	
	// 회원 정보 수정 - pw, email, region
	@Transactional
    public Long updateMember(Long memberIdx, MemberDTO.Update dto) {
        Member member = memberRepository.findByMemberIdx(memberIdx)
        								.orElseThrow(() -> new CUserNotFoundException("Member with memberIdx: " + memberIdx + " is not valid"));

        member.update(dto.getPw(), dto.getEmail(), dto.getRegion());

        return memberIdx;
    }
	
	// 회원 탈퇴
	@Transactional
    public void deleteMember(Long memberIdx) {
        Member member = memberRepository.findByMemberIdx(memberIdx)
                						.orElseThrow(() -> new CUserNotFoundException("Member with memberIdx: " + memberIdx + " is not valid"));

        member.delete(1);
    }

}
