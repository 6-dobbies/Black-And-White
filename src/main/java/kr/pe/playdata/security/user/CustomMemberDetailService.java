package kr.pe.playdata.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomMemberDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public Member loadMemberByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId)
//							   .orElseThrow(() -> new IllegalArgumentException("Member with memberId: " + memberId + " is not valid"))
							   ;
	}

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		return memberRepository.findByMemberId(memberId)
//							   .orElseThrow(() -> new IllegalArgumentException("Member with memberId: " + memberId + " is not valid"))
							   ;
	}

}
