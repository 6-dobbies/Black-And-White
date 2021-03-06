package kr.pe.playdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.exception.CUserNotFoundException;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Tier;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.repository.TierRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TierService {
	
	private final TierRepository tierRepository;
	private final MemberRepository memberRepository;
	
	// 회원 전적 조회
//	@Transactional(readOnly = true)
//	public String findByMemberIdx(Long memberIdx) {
//		
//		Member entity = memberRepository.findByMemberIdx(memberIdx).orElseThrow(CUserNotFoundException::new);
//		Tier tier = tierRepository.findByMember(entity);
//		TierResponse membertier = new ResponseDTO.TierResponse(tier);
//		
//		return membertier.getWin() + "승 " + membertier.getDraw() + "무 " + membertier.getLoss() + "패 (" + membertier.getPlay() + "판)";
//	}
	
	// 회원 전적 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.TierListResponse> findByMember(Long memberIdx) {
		
		Member entity = memberRepository.findByMemberIdx(memberIdx).orElseThrow(CUserNotFoundException::new);
		return tierRepository.findByMember(entity)
							 .stream()
							 .map(ResponseDTO.TierListResponse::new)
							 .collect(Collectors.toList());
	}
	
	// 회원 가입시 전적 저장
	@Transactional(rollbackFor = Exception.class)
	public Tier saveTier(Long memberIdx) {

		Member member = memberRepository.findByMemberIdx(memberIdx).orElseThrow(CUserNotFoundException::new);
		int win = 0;
		int draw = 0;
		int loss = 0;
		int play = 0;

		Tier dto = Tier.builder().member(member).win(win).draw(draw).loss(loss).play(play).build();

		return tierRepository.save(dto);

	}

}
