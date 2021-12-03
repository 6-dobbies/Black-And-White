package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
//@SequenceGenerator(name="member_seq", sequenceName="member_seq", initialValue=1, allocationSize=1)
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)			//mysql에서 사용하기!! oracle은 불가
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="member_seq")	//나중에 삭제하기
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
	
	public Member update(String pw, String nickname, String email, String region) {
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.region = region;
		
		return this;
	}
	
	public Member delete(int del) {
		this.del = 1;
		
		return this;
	}
	
}
