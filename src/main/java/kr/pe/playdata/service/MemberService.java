package kr.pe.playdata.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.pe.playdata.config.security.JwtTokenProvider;
import kr.pe.playdata.exception.CIdSigninFailedException;
import kr.pe.playdata.exception.CUserNotFoundException;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.dto.MemberDTO;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.CheckRepository;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final CheckRepository checkRepository;
	private final JwtTokenProvider jwtTokenProvider;
	
	// 회원 1명 조회 - memberIdx
	@Transactional(readOnly = true)
	public ResponseDTO.MemberResponse findByMemberIdx(Long memberIdx) {
		
		Member entity = memberRepository.findByMemberIdx(memberIdx).orElseThrow(CUserNotFoundException::new);
		
		return new ResponseDTO.MemberResponse(entity);
		
	}
	
	// 회원 1명 조회 - nickname
	@Transactional(readOnly = true)
    public ResponseDTO.MemberResponse findByNickname(String nickname) {
		
        Member entity = memberRepository.findByNickname(nickname).orElseThrow(CUserNotFoundException::new);
        
        return new ResponseDTO.MemberResponse(entity);
        
    }
	
	// 회원 list 조회 - nickname 일부
	@Transactional(readOnly = true)
	public List<ResponseDTO.MemberListResponse> findByNicknameContaining(String nickname) {
		return memberRepository.findByNicknameContaining(nickname)
							   .stream()
							   .map(ResponseDTO.MemberListResponse::new)
							   .collect(Collectors.toList());
	}
	
	// 회원 전체 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.MemberListResponse> findMemberAll() {
		return memberRepository.findAll()
							   .stream()
							   .map(ResponseDTO.MemberListResponse::new)
							   .collect(Collectors.toList());
	}
	
	// 회원 저장
	@Transactional(rollbackFor = Exception.class)
	public Long saveMember(String data) throws ParseException {

		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
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
		int del = 0;
		List<String> role = Collections.singletonList("normal");

		Member dto = Member.builder().memberId(memberId).pw(pw).pwQuestion(pwQuestion).pwAnswer(pwAnswer)
				.nickname(nickname).birthYear(birthYear).email(email).gender(gender).region(region).role(role).del(del)
				.build();

		return memberRepository.save(dto).getMemberIdx();

	}
	
	// 회원 정보 수정 - pw, email, region
	@Transactional(rollbackFor = Exception.class)
	public Long updateMember(Long memberIdx, String data) throws ParseException {
		
		Member member = memberRepository.findByMemberIdx(memberIdx).orElseThrow(CUserNotFoundException::new);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");

		String pw = (String) json2.get("pw");
		String email = (String) json2.get("email");
		String region = (String) json2.get("region");

		member.update(pw, email, region);
		
		return memberIdx;

	}
	
	// 회원 탈퇴
	@Transactional
    public void deleteMember(Long memberIdx) {
		
        Member member = memberRepository.findByMemberIdx(memberIdx).orElseThrow(CUserNotFoundException::new);

        member.delete(1);
        
    }
	
	// 회원 list 조회 - del
	@Transactional(readOnly = true)
	public List<ResponseDTO.MemberListResponse> findByDel(int del) {
		return memberRepository.findByDel(del)
							   .stream()
							   .map(ResponseDTO.MemberListResponse::new)
							   .collect(Collectors.toList());
	}
	
	// 회원 id 중복 체크 - true면 사용 가능
	@Transactional(readOnly = true)
	public boolean checkMemberId(String memberId) {

		boolean result = false;
		List<Member> member = checkRepository.findByMemberId(memberId);

		if (member.size() == 0) {
			result = true;
		}
		
		return result;
		
	}
	
	// 회원 nickname 중복 체크 - true면 사용 가능
	@Transactional(readOnly = true)
	public boolean checkNickname(String nickname) {

		boolean result = false;
		List<Member> member = checkRepository.findByNickname(nickname);

		if (member.size() == 0) {
			result = true;
		}
		
		return result;

	}
	
	// 회원 email 중복 체크 - true면 사용 가능
	@Transactional(readOnly = true)
	public boolean checkEmail(String email) {
		
		boolean result = false;
		List<Member> member = checkRepository.findByEmail(email);

		if (member.size() == 0) {
			result = true;
		}
		
		return result;
		
	}
	
	// 회원 memberId 찾기 - email, birthYear
	@Transactional(readOnly = true)
	public String findMemberIdByEmailAndBirthYear(String email, String birthYear) {
		
		Member member = memberRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
		
		if(birthYear.equals(member.getBirthYear())) {
			return member.getMemberId();
		}
		
		return "이메일 또는 출생연도가 일치하지 않습니다:(";
		
	}
	
	// 회원 임시 pw 발급 & 이메일 발송
	@Transactional(rollbackFor = Exception.class)
	public ResponseDTO.MemberResponse tempoPw(String memberId, String pwQuestion, String pwAnswer) {
		
		Member member = memberRepository.findByMemberId(memberId).orElseThrow(CUserNotFoundException::new);
		Random random = new Random();

		if (pwQuestion.equals(member.getPwQuestion()) && pwAnswer.equals(member.getPwAnswer())) {
			member.tempoPw(Integer.toString(random.nextInt(999999)));
			
			return new ResponseDTO.MemberResponse(member);
		}
		
		return null;
		
	}
	
	// 회원 로그인
	@PostMapping("/members/login")
	public MemberDTO.Authenticate login(@RequestBody MemberDTO.Login reqMember) {

		Member member = memberRepository.findByMemberId(reqMember.getMemberId()).orElseThrow(CUserNotFoundException::new);

//		if (!passwordEncoder.matches(reqMember.getPw(), member.getPw())) {	// dml을 직접 insert하면 pw가 encoded되지 않은 값
		if (reqMember.getPw().equals(member.getPw())) {
			
			String token = jwtTokenProvider.createToken(String.valueOf(member.getMemberIdx()), member.getRole());
			String memberIdx = Long.toString(member.getMemberIdx());
			boolean ismanager = false;
			
			if(member.getRole().contains("manager")) {
				ismanager = true;
			}
			
			return new MemberDTO.Authenticate(token, memberIdx, ismanager);
			
		}
		
		throw new CIdSigninFailedException();

	}

}
