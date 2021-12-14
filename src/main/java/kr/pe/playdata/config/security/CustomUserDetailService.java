package kr.pe.playdata.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.pe.playdata.exception.CUserNotFoundException;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private final MemberRepository memberRepository;

	public UserDetails loadUserByUsername(String memberId) {
		return memberRepository.findById(Long.valueOf(memberId)).orElseThrow(CUserNotFoundException::new);
	}
	
}
