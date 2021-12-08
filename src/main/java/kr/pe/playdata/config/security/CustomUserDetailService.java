package kr.pe.playdata.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberJpaRepo;

    public UserDetails loadUserByUsername(String userPk) {
        return memberJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(NullPointerException::new);
    }
}