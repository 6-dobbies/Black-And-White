package kr.pe.playdata.config.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private String secretKey = "dobbyisfree";
	private long tokenValidTime = 24 * 60 * 60 * 1000L;		// 토큰 유효시간 하루
	private final UserDetailsService userDetailsService;

	// 객체 초기화, secretKey를 Base64로 인코딩한다.
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	// JWT 토큰 생성
	public String createToken(String userPk, List<String> roles) {

		Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
		claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
		Date now = new Date();

		return Jwts.builder().setClaims(claims) // 정보 저장
							 .setIssuedAt(now) // 토큰 발행 일자 정보
							 .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
							 .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
							 .compact();
	}

//========================================================================================================

	// JWT 토큰에서 인증 정보 조회 - 토큰을 통해 유저이름을 가져온뒤 그 유저이름을 이용하여 유저를 불러오는것
	// loadUserByUsername을 pk를 팔요로 하는 함수와 추가적으로 연결해도 될듯
	public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

	// 토큰에서 회원 정보 추출 - decoding
	public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

//		// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
//	    // 리퀘스트를 받기위해서는 URI 지정이 필요
//	    public String resolveToken(@RequestBody String request) {
//	        return request;
//	    }

//==================================================================================================
	
	// Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt토큰"
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}

	// 토큰의 유효성 + 만료일자 확인
	public boolean validateToken(String jwtToken) {

		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);

			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}

	}
	
}
