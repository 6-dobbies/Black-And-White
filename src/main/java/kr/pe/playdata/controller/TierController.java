package kr.pe.playdata.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.domain.Tier;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.service.ResponseService;
import kr.pe.playdata.service.TierService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
public class TierController {

	private final ResponseService responseService;
	private final TierService tierService;

	// 회원 가입시 전적 저장
	@PostMapping("/members/tier/{memberIdx}")
	public SingleResult<Tier> saveTier(@PathVariable Long memberIdx) {
		return responseService.getSingleResult(tierService.saveTier(memberIdx));
	}

}
