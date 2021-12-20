package kr.pe.playdata.model.dto;

import java.util.Collections;
import java.util.List;

import kr.pe.playdata.model.domain.Member;
import lombok.Builder;
import lombok.Getter;

public class MemberDTO {

	@Getter
	public static class Create {
		private String memberId;
		private String pw;
		private String pwQuestion;
		private String pwAnswer;
		private String nickname;
		private String birthYear;
		private String email;
		private String gender;
		private String region;
		private String tier;
		private List<String> role;
		private int del;

		@Builder
		public Create(String memberId, String pw, String pwQuestion, String pwAnswer, String nickname, 
					  String birthYear, String email, String gender, String region, String tier) {
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
			this.role = Collections.singletonList("normal");
			this.del = 0;
		}

		public Member toEntity() {
			return Member.builder()
						 .memberId(memberId)
						 .pw(pw)
						 .pwQuestion(pwQuestion)
						 .pwAnswer(pwAnswer)
						 .nickname(nickname)
						 .birthYear(birthYear)
						 .email(email)
						 .gender(gender)
						 .region(region)
						 .tier(tier)
						 .role(role)
						 .del(del)
						 .build();
		}
	}

	@Getter
	public static class Update {
		private String pw;
		private String nickname;
		private String email;
		private String region;

		@Builder
		public Update(String pw, String nickname, String email, String region) {
			this.pw = pw;
			this.nickname = nickname;
			this.email = email;
			this.region = region;
		}
	}

	@Getter
	public static class Delete {
		private int del;

		@Builder
		public Delete(int del) {
			this.del = 1;
		}
	}
	
	@Getter
	public static class Login {
		private String memberId;
		private String pw;

		@Builder
		public Login(String memberId, String pw) {
			this.memberId = memberId;
			this.pw = pw;
		}
	}
	
	@Getter
	public static class CheckMemberId {
		private String memberId;
	}
	
	@Getter
	public static class CheckNickname {
		private String nickname;
	}
	
	@Getter
	public static class CheckEmail {
		private String email;
	}
	
	@Getter
	public static class FindMemberId {
		private String email;
		private String birthYear;
	}

	@Getter
	public static class TempoPw {
		private String memberId;
		private String pwQuestion;
		private String pwAnswer;
	}
	
	@Getter
	public static class SendMail {
		private String memberId;
		private String pwQuestion;
		private String pwAnswer;
		private String email;
		private String pw;
	}

}
