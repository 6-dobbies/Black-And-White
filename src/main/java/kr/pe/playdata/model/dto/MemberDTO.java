package kr.pe.playdata.model.dto;

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
		private String role;
		private String out;
		
		@Builder
		public Create(String memberId, String pw, String pwQuestion, String pwAnswer, String nickname, String birthYear, String email, String gender, String region, String tier, String role, String out) {
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
	public static class Get {
		private String nickname;
	}
	
	@Getter
	public static class Delete {
		private String out;
		
		@Builder
		public Delete(String out) {
			this.out = out;
		}
	}
	
}
