package kr.pe.playdata.model.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import kr.pe.playdata.model.dto.MemberDTO.Create;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class Member implements UserDetails {
	
	/**
	 * UID값이 달라질경우 같은 클래스라도 호출이 되지 않으므로 지정
	 */
//	private static final long serialVersionUID = 7863300068250868438L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_idx")
	private Long memberIdx;
	
	@NotNull
	@Column(name = "member_id")
	private String memberId;
	
	@NotNull
	private String pw;
	
	@NotNull
	@Column(name = "pw_question")
	private String pwQuestion;
	
	@NotNull
	@Column(name = "pw_answer")
	private String pwAnswer;
	
	@NotNull
	private String nickname;
	
	@NotNull
	@Column(name = "birth_year")
	private String birthYear;
	
	@NotNull
	private String email;
	
	@NotNull
	private String gender;
	
	@NotNull
	private String region;
	
	@NotNull
	private String tier;
	
	@NotNull
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> role = new ArrayList<>();
	
	@NotNull
	private int del;
	
	@OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Post> postList;
	
	@Builder
	public Member(String memberId, 
					String pw, 
					String pwQuestion, 
					String pwAnswer, 
					String nickname, 
					String birthYear, 
					String email, 
					String gender, 
					String region, 
					String tier, 
					List<String> role, 
					int del) {
		this.memberId = memberId;
		this.pw = pw;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
		this.nickname = nickname;
		this.birthYear = birthYear;
		this.email = email;
		this.gender = gender;
		this.region = region;
		this.tier = tier;
		this.role = Collections.singletonList("ROLE_USER");
		this.del = 0;
	}
	
	public Member update(String pw, String email, String region) {
		this.pw = pw;
		this.email = email;
		this.region = region;
		
		return this;
	}
	
	public Member delete(int del) {
		this.del = 1;
		
		return this;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.role.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.memberId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return null;
	}
	
}
