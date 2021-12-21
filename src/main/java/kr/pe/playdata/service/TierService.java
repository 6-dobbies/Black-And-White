package kr.pe.playdata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Tier;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.dto.ResponseDTO.TierResponse;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.repository.TierRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TierService {
	
	private final TierRepository tierRepository;
	private final MemberRepository memberRepository;
	
	// 회원 전적 조회
	@Transactional(readOnly = true)
	public String findByMember(String nickname) {
		Member entity = memberRepository.findByNickname(nickname);
		Tier tier = tierRepository.findByMember(entity);
		TierResponse membertier = new ResponseDTO.TierResponse(tier);
		
		return membertier.getWin() + "승 " + membertier.getDraw() + "무 " + membertier.getLoss() + "패 (" + membertier.getPlay() + "판)";
//		return new ResponseDTO.TierResponse(tier);
	}
	
	// 회원 가입시 전적 저장
//	@Transactional(rollbackFor = Exception.class)
//	public Long saveTier(String data) {
//		
//		JSONParser jsonParser = new JSONParser();
//		JSONObject json;
//		
//		try {
//			json = (JSONObject) jsonParser.parse(data);
//			JSONObject json2 = (JSONObject) json.get("data");
//			Member member = memberRepository.findByNickname((String) json2.get("member"));
//			int win = 0;
//			int draw = 0;
//			int loss = 0;
//			int play = 0;
//			
//			Tier dto = Tier.builder().member(member).win(win).draw(draw).loss(loss).play(play).build();
//			
//			return tierRepository.save(dto).getTierIdx();
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		return 0L;
//	}
	
	@Transactional(rollbackFor = Exception.class)
	public Tier saveTier(Long memberIdx) {

		Member member = memberRepository.findByMemberIdx(memberIdx);
		int win = 0;
		int draw = 0;
		int loss = 0;
		int play = 0;

		Tier dto = Tier.builder().member(member).win(win).draw(draw).loss(loss).play(play).build();

		return tierRepository.save(dto);

	}

}