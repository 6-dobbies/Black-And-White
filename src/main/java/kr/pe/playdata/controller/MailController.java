package kr.pe.playdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.service.MailService;
import kr.pe.playdata.service.MemberService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
public class MailController {

	private final MailService mailService;
	private final MemberService memberService;
	private final ResponseService responseService;
	private final MemberRepository memberRepository;

	@PostMapping("/email")
	public SingleResult<String> saveLocation(@RequestBody MemberDTO.TempoPw dto) {
		
		Member member = memberRepository.findByMemberId(dto.getMemberId());
		
		memberService.tempoPw(dto.getMemberId(), dto.getPwQuestion(), dto.getPwAnswer());
		mailService.sendEmail(member.getEmail(), "임시 비밀번호 안내 메일입니다:)", "임시로 발급된 비밀번호는 " + member.getPw() + "입니다.");

		return responseService.getSingleResult("테스트:)");
		
	}

}
