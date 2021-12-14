package kr.pe.playdata.controller;

import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.dto.ResponseDTO.MemberListResponse;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.service.MemberService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
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
	public SingleResult<Long> saveMember(@RequestBody String Data) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(Data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		String memberId = (String) json2.get("memberId");
		String pw = (String) json2.get("pw");
		String pwQuestion = (String) json2.get("pwQuestion");
		String pwAnswer = (String) json2.get("pwAnswer");
		String nickname = (String) json2.get("nickname");
		String birthYear = (String) json2.get("birthYear");
		String gender = (String) json2.get("gender");
		String email = (String) json2.get("email");
		String region = (String) json2.get("region");
		String tier = (String) json2.get("tier");
		int del = 0;
		List<String> role = Collections.singletonList("normal");

		Member dto = Member.builder()
						   .memberId(memberId)
						   .pw(pw)
						   .pwQuestion(pwQuestion)
						   .pwAnswer(pwAnswer)
						   .nickname(nickname)
						   .birthYear(birthYear)
						   .email(email)
						   .gender(gender)
						   .region(region)
						   .tier(tier)
						   .role(role)
						   .del(del)
						   .build();
		
		return responseService.getSingleResult(memberService.saveMember(dto));
	}

	// 회원 정보 수정 - pw, email, region
	@PutMapping("/members/{memberIdx}")
	public SingleResult<Long> updateMember(@PathVariable Long memberIdx, @RequestBody String Data) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(Data);
		JSONObject json2 = (JSONObject) json.get("data");

		String pw = (String) json2.get("pw");
		String email = (String) json2.get("email");
		String region = (String) json2.get("region");

		Member dto = Member.builder().pw(pw).email(email).region(region).build();
		
		return responseService.getSingleResult(memberService.updateMember(memberIdx, dto));
	}

	// 회원 탈퇴
	@PatchMapping("/members/{memberIdx}") // @Requestbody를 통해 토큰을 받고 그것을 검증해서 멤버 탈퇴 자격이 있나 확인
	public SingleResult<Long> deleteMember(@PathVariable Long memberIdx) {
		
		memberService.deleteMember(memberIdx);

		return responseService.getSingleResult(memberIdx);
	}
	
	// 게시글 list 조회 - del
	@GetMapping("/members/del/{del}")
	public ListResult<MemberListResponse> getPostListDel(@PathVariable int del) {
		return responseService.getListResult(memberService.findByDel(del));
	}

}
