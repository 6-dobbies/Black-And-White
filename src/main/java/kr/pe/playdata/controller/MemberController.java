package kr.pe.playdata.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.pe.playdata.config.security.JwtLoginToken.JwtTokenProvider;
import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.service.MemberService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
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
	@RequestMapping(consumes = "application/json")
	public SingleResult<ResponseDTO.MemberResponse> getMemberByIdx(@PathVariable Long memberIdx) {
		System.out.println("ee");
		return rs.getSingleResult(memberService.findByMemberIdx(memberIdx));
	}
	
	// 밑에 nickname 일부 검색해서 조회하는 메서드 있으면 이 메서드는 삭제해도 될 듯...? - service & repository도 같이 삭제하기
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
	public SingleResult<Long> saveMember(@RequestBody MemberDTO.Create dto) {
		return rs.getSingleResult(memberService.saveMember(dto));
	}
	
	// 회원 정보 수정 - pw, email, region
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@PutMapping("/members/{memberIdx}")
    public SingleResult<Long> updateMember(@PathVariable Long memberIdx, @RequestBody MemberDTO.Update dto) {
        return rs.getSingleResult(memberService.updateMember(memberIdx, dto));
    }
	
	// 회원 탈퇴
	@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
	})
	@PatchMapping("/members/{memberIdx}")
    public SingleResult<Long> deleteMember(@PathVariable Long memberIdx) {
		memberService.deleteMember(memberIdx);
		
        return rs.getSingleResult(memberIdx);
    }
	
}
