package kr.pe.playdata.controller;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.dto.ResponseDTO.MemberListResponse;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.service.MemberService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://ec2-13-125-131-254.ap-northeast-2.compute.amazonaws.com:8081")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;
	private final ResponseService responseService;

	// 회원 1명 조회 - memberIdx
	@GetMapping("/members/idx/{memberIdx}")
	public SingleResult<ResponseDTO.MemberResponse> getMemberByIdx(@PathVariable Long memberIdx) {
		return responseService.getSingleResult(memberService.findByMemberIdx(memberIdx));
	}

	// 회원 1명 조회 - nickname
	@GetMapping("/members/nickname/{nickname}")
	public SingleResult<ResponseDTO.MemberResponse> getMemberByNickname(@PathVariable String nickname) {
		return responseService.getSingleResult(memberService.findByNickname(nickname));
	}

	// 회원 list 조회 - nickname 일부
	@GetMapping("/members/nicknamecon/{nickname}")
	public ListResult<ResponseDTO.MemberListResponse> getMemberByNicknameCon(@PathVariable String nickname) {
		return responseService.getListResult(memberService.findByNicknameContaining(nickname));
	}

	// 회원 전체 조회
	@GetMapping("/members/all")
	public ListResult<ResponseDTO.MemberListResponse> getMemberAll() {
		return responseService.getListResult(memberService.findMemberAll());
	}

	// 회원 저장
	@PostMapping("/member")
	public SingleResult<Long> saveMember(@RequestBody String data) throws ParseException {
		return responseService.getSingleResult(memberService.saveMember(data));
	}

	// 회원 정보 수정 - pw, email, region
	@PutMapping("/members/{memberIdx}")
	public SingleResult<Long> updateMember(@PathVariable Long memberIdx, @RequestBody String data) throws ParseException {
		return responseService.getSingleResult(memberService.updateMember(memberIdx, data));
	}

	// 회원 탈퇴
	@PatchMapping("/members/{memberIdx}") // @Requestbody를 통해 토큰을 받고 그것을 검증해서 멤버 탈퇴 자격이 있나 확인
	public SingleResult<Long> deleteMember(@PathVariable Long memberIdx) {
		
		memberService.deleteMember(memberIdx);

		return responseService.getSingleResult(memberIdx);
		
	}
	
	// 회원 list 조회 - del
	@GetMapping("/members/del/{del}")
	public ListResult<MemberListResponse> getMemberListDel(@PathVariable int del) {
		return responseService.getListResult(memberService.findByDel(del));
	}
	
	// 회원 id 중복 체크
	@PostMapping("/members/check/memberid")
	public boolean checkMemberId(@RequestBody MemberDTO.CheckMemberId dto) {
		return memberService.checkMemberId(dto.getMemberId());
	}
	
	// 회원 nickname 중복 체크
	@PostMapping("/members/check/nickname")
	public boolean checkNickname(@RequestBody MemberDTO.CheckNickname dto) {
		return memberService.checkNickname(dto.getNickname());
	}
	
	// 회원 email 중복 체크
	@PostMapping("/members/check/email")
    public boolean checkEmail(@RequestBody MemberDTO.CheckEmail dto) {
        return memberService.checkEmail(dto.getEmail());
    }
	
	// 회원 아이디 찾기 - email, birthYear
	@PostMapping("/members/find/memberid")
	public SingleResult<String> findMemberIdByEmailAndBirthYear(@RequestBody MemberDTO.FindMemberId dto) {
		return responseService.getSingleResult(memberService.findMemberIdByEmailAndBirthYear(dto.getEmail(), dto.getBirthYear()));
	}
	
	// 회원 로그인
	@PostMapping("/members/login")
	public SingleResult<MemberDTO.Authenticate> login(@RequestBody MemberDTO.Login dto) {
		return responseService.getSingleResult(memberService.login(dto));
	}

}
