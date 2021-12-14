package kr.pe.playdata.controller;

import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONString;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.pe.playdata.config.security.JwtLoginToken.JwtTokenProvider;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Member.MemberBuilder;
import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.dto.MemberDTO.Create;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.service.MemberService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin	// Vue port번호인 8081로 지정해야 됨
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberRepository memberRepo;
	private final MemberService memberService;
	private final ResponseService rs;
	
	
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	// 회원 조회 - memberIdx
	@GetMapping("/members/idx/{memberIdx}") 

	public SingleResult<ResponseDTO.MemberResponse> getMemberByIdx(@PathVariable Long memberIdx) {
		return rs.getSingleResult(memberService.findByMemberIdx(memberIdx));
	}
	
	@GetMapping("/members/id/{memberId}") 

	public SingleResult<ResponseDTO.MemberResponse> getMemberById(@PathVariable String memberId) {
		return rs.getSingleResult(memberService.findByMemberId(memberId));
	}
	
	@GetMapping("/members/email/{memberEmail")

	public SingleResult<ResponseDTO.MemberResponse> getMemberByEmail(@PathVariable String email) {
		return rs.getSingleResult(memberService.findByEmail(email));
	}

	// 밑에 nickname 일부 검색해서 조회하는 메서드 있으면 이 메서드는 삭제해도 될 듯...? - service & repository도
	// 같이 삭제하기
	// 회원 조회 - nickname
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/members/nickname/{nickname}")
	public SingleResult<ResponseDTO.MemberResponse> getMemberByNickname(@PathVariable String nickname) {
		return rs.getSingleResult(memberService.findByNickname(nickname));
	}

	// 회원 조회 - nickname 일부
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/members/nicknamecon/{nickname}")
	public ListResult<ResponseDTO.MemberListResponse> getMemberByNicknameCon(@PathVariable String nickname) {
		return rs.getListResult(memberService.findByNicknameContaining(nickname));
	}

	// 회원 전체 조회
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@GetMapping("/members/all")
	public ListResult<ResponseDTO.MemberListResponse> getMemberAll() {
		return rs.getListResult(memberService.findMemberAll());
	}

	// 회원 저장
	@PostMapping("/member")
//	@RequestMapping(consumes = "application/json") // 멤버 생성시에 json파일로 요청해주세요
	public SingleResult<Long> saveMember(@RequestBody String Data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(Data);
		JSONObject json2 = (JSONObject) json.get("data");
		String memberId = (String) json2.get("memberId");
		String pw = (String) json2.get("memberId");
		String pwQuestion = (String) json2.get("pwQuestion");
		String pwAnswer = (String) json2.get("pwAnswer");
		String nickname = (String) json2.get("nickname");
		String birthYear = (String) json2.get("birthYear");
		String gender = (String) json2.get("gender");
		String email = (String) json2.get("email");
		String region = (String) json2.get("region");
		String tier = (String) json2.get("tier");
		List<String> role = Collections.singletonList("ROLE_USER");
		int del = 0;

		Member dto = Member.builder().memberId(memberId).pw(pw).pwQuestion(pwQuestion).pwAnswer(pwAnswer).nickname(nickname).birthYear(birthYear).email(email).gender(gender).region(region).tier(tier).role(role).del(del).build();
		return rs.getSingleResult(memberService.saveMember(dto));
	}

	// 회원 정보 수정 - pw, email, region
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@PutMapping("/members/{memberIdx}")
    public SingleResult<Long> updateMember(@PathVariable Long memberIdx, @RequestBody String Data) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(Data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		String pw = (String) json2.get("memberId");
		String email = (String) json2.get("email");
		String region = (String) json2.get("region");
		
		Member dto = Member.builder().pw(pw).email(email).region(region).build();
        return rs.getSingleResult(memberService.updateMember(memberIdx, dto));
    }
	
	// 회원 탈퇴
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@PatchMapping("/members/{memberIdx}") // @Requestbody를 통해 토큰을 받고 그것을 검증해서 멤버 탈퇴 자격이 있나 검증
    public SingleResult<Long> deleteMember(@PathVariable Long memberIdx) {
		memberService.deleteMember(memberIdx);
		
        return rs.getSingleResult(memberIdx);
    }
	
}
