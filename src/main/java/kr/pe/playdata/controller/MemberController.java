package kr.pe.playdata.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.service.MemberService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000") // CORS때문에 교차출처에 표시
@RequiredArgsConstructor
@RestController
// @RequestMapping("/con") // /mcon/method 
public class MemberController {
	
	private final MemberService memberService;
	
	// 회원 한 명 조회 - memberIdx
	@GetMapping("/member/idx/{memberIdx}")
	public ResponseDTO.MemberResponse getMemberOneIdx(@PathVariable Long memberIdx) {
		return memberService.findByMemberIdx(memberIdx);
	}
	
	// 회원 한 명 조회 - nickname
	@GetMapping("/member/nickname/{nickname}")
	public ResponseDTO.MemberResponse getMemberOneNickname(@PathVariable String nickname) {
		return memberService.findByNickname(nickname);
	}
	
	// 회원 한 명 조회 - nickname 일부
	
	
	// 회원 전체 조회
	@GetMapping("/member/all")
	public List<ResponseDTO.MemberListResponse> getMemberAll() {
		return memberService.findMemberAll();
	}
	
	// 회원 

	
}
