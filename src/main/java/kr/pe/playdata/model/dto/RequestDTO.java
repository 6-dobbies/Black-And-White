package kr.pe.playdata.model.dto;

import lombok.Builder;
import lombok.Getter;

public class RequestDTO {
	
	@Getter
	public static class MemberUpdate {
		private String pw;
		private String nickname;
		private String email;
		private String region;
		
		@Builder
		public MemberUpdate(String pw, String nickname, String email, String region) {
			this.pw = pw;
			this.nickname = nickname;
			this.email = email;
			this.region = region;
		}
	}
	
}
