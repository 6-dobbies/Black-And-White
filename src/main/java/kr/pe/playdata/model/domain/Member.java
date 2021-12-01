package kr.pe.playdata.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class Member {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)			//mysql에서 사용하기!! oracle은 불가
	@Column(name = "member_idx")
	private Long memberIdx;
	
	@NotNull
	@Column(name = "member_id")
	private String memberId;
	
	@NotNull
	@Column(name = "pw")
	private String pw;
	
	@NotNull
	@Column(name = "pw_question")
	private String pwQuestion;
	
	@NotNull
	@Column(name = "pw_answer")
	private String pwAnswer;
	
	@NotNull
	@Column(name = "nickname")
	private String nickname;
	
	@NotNull
	@Column(name = "birth_year")
	private String birthYear;
	
	@NotNull
	@Column(name = "email")
	private String email;
	
	@NotNull
	@Column(name = "gender") 
	private String gender;
	
	@NotNull
	@Column(name = "region")
	private String region;
	
	@NotNull
	@Column(name = "tier")
	private String tier;
	
	@NotNull
	@Column(name = "role")
	private String role;
	
	@NotNull
	@Column(name = "out")
	private String out;
	
	@Builder
	public Member(String memberId, String pw, String pwQuestion, String pwAnswer, String nickname, String birthYear, String email, String gender, String region, String tier, String role, String out) {
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
		this.out = out;
	}
	
	public Member update(String pw, String nickname, String email, String region) {
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.region = region;
		
		return this;
	}
	
	public Member delete(String out) {
		this.out = out;
		
		return this;
	}
	
	
//	@Override
//	public String toString() {
//
//		return "{\"userEmail\":\"" + userEmail + "\", "
////				+ "\"userPassword\":\"" + userPassword + "\", "
//				+ "\"userNickname\":\"" + userNickname+ "\", "
//				+ "\"roles\":\"" + roles + "\", "
//				+ "\"userOut\":" + userOut + ", "
//				+ "\"assignDate\":\"" + assignDate + "\", "
//				+ "\"outDate\":\"" + outDate + "\"}";
//	}
//	
	
}
