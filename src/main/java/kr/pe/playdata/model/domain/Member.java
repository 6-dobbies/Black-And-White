package kr.pe.playdata.model.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	private static final long serialVersionUID = 7863300068250868438L;


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
	private String role;
	
	@NotNull
	private int del;
	
	@OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Post> postList;
	
	@Builder
	public Member(String memberId, String pw, String pwQuestion, String pwAnswer, String nickname, String birthYear, String email, String gender, String region, String tier, String role, int del) {
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
		this.role = role;
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

	//// 문제 생길수도 있음
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<String> roles = new ArrayList<>();
		roles.add(this.role);
		
		return roles.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
