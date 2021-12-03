package kr.pe.playdata.model.dto;

import kr.pe.playdata.model.domain.Member;
import lombok.Getter;

public class ResponseDTO {
	
	@Getter
    public static class MemberResponse {
		private String memberId;
		private String pw;
		private String pwQuestion;
		private String pwAnswer;
		private String nickname;
		private String birthYear;
		private String email;
		private String gender;
		private String region;

        public MemberResponse(Member entity) {
            this.memberId = entity.getMemberId();
            this.pw = entity.getPw();
            this.pwQuestion = entity.getPwQuestion();
            this.pwAnswer = entity.getPwAnswer();
            this.nickname = entity.getNickname();
            this.birthYear = entity.getBirthYear();
            this.email = entity.getEmail();
            this.gender = entity.getGender();
            this.region = entity.getRegion();
        }
    }
	
}
