package kr.pe.playdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.response.SingleResult;
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

	@PatchMapping("/email")
	public SingleResult<String> saveLocation(@RequestBody MemberDTO.TempoPw dto) {

		String email = memberService.tempoPw(dto.getMemberId(), dto.getPwQuestion(), dto.getPwAnswer());

		if (email != "false") {
			mailService.sendEmail(email, "임시 비밀번호 안내 메일입니다:)", "임시로 발급된 비밀번호는 " + dto.getPw() + "입니다.");
			return responseService.getSingleResult("메일이 발송되었습니다");
		} else {
			return responseService.getSingleResult("아이디 또는 질문 또는 답변이 일치하지 않습니다:(");
		}

	}

}
