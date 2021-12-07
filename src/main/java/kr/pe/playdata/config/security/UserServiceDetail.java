package kr.pe.playdata.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceDetail implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws NullPointerException {
		return null;
	}
}
