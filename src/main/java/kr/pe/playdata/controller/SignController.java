package kr.pe.playdata.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.config.security.JwtLoginToken.JwtTokenProvider;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
@RestController
public class SignController {

	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository userJpaRepo;
	
	// 회원 로그인
	@PostMapping("/members/login")
	public String login(@RequestParam String id, @RequestParam String password) {
		
		Member member = userJpaRepo.findByMemberId(id)
								   .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
		
		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new IllegalArgumentException("잘못된 비밀번호입니다.");
		}
		
//====== role을 어디서 사용하는지? ====================================================================================================
		List<String> role = member.getRole();
		
		return jwtTokenProvider.createToken(String.valueOf(member.getMemberIdx()), member.getRole());
		
	}

}
