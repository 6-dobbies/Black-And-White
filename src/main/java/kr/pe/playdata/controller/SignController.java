package kr.pe.playdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.config.security.JwtTokenProvider;
import kr.pe.playdata.exception.CIdSigninFailedException;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
public class SignController {

	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final MemberRepository memberRepository;
//	private final PasswordEncoder passwordEncoder;

	// 회원 로그인
	@PostMapping("/members/login")
	public SingleResult<String> login(@RequestBody Member reqMember) {

		Member member = memberRepository.findByMemberId(reqMember.getMemberId())
//										.orElseThrow(() -> new CIdSigninFailedException("Member with memberId: " + reqMember.getMemberId() + " is not valid"))
										;

		if (!reqMember.getPw().equals(member.getPw())) {
//		if (!passwordEncoder.matches(reqMember.getPw(), member.getPw())) {	// dml insert할 때
//			throw new CIdSigninFailedException("잘못된 비밀번호입니다.");
			throw new CIdSigninFailedException();
		}
		
		return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(member.getMemberIdx()), member.getRole()));

	}
	
}
