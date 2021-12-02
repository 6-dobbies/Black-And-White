package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
// oracle이라서 @SequenceGenerator 사용해야함. mysql로 바꾸면 삭제하기
@SequenceGenerator(name="member_seq", sequenceName="member_seq", initialValue=1, allocationSize=1)
@Table(name = "member")
public class Member {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)			//mysql에서 사용하기!! oracle은 불가
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="member_seq")	//나중에 삭제하기
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
	private int out;
	
	@OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Post> postList;
	
	@Builder
	public Member(String memberId, String pw, String pwQuestion, String pwAnswer, String nickname, String birthYear, String email, String gender, String region, String tier, String role, int out) {
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
		this.out = 0;
	}
	
	public Member update(String pw, String nickname, String email, String region) {
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.region = region;
		
		return this;
	}
	
	public Member delete(int out) {
		this.out = 1;
		
		return this;
	}
	
}
