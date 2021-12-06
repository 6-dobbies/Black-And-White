package kr.pe.playdata.model.dto;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;
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
	
	@Getter
	public static class MemberListResponse {
		private Long memberIdx;
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
		private int del;
		
		public MemberListResponse(Member entity) {
			this.memberIdx = entity.getMemberIdx();
			this.memberId = entity.getMemberId();
			this.pw = entity.getPw();
			this.pwQuestion = entity.getPwQuestion();
			this.pwAnswer = entity.getPwAnswer();
			this.nickname = entity.getNickname();
			this.birthYear = entity.getBirthYear();
			this.email = entity.getEmail();
			this.gender = entity.getGender();
			this.region = entity.getRegion();
			this.tier = entity.getTier();
			this.role = entity.getRole();
			this.del = entity.getDel();
		}
	}
	
	@Getter
    public static class PostResponse {
		private Long memberIdx;
		private Long postIdx;
		private String category;
		private String title;
		private String content;
		private String postImage;
		
		public PostResponse(Post entity) {
			this.memberIdx = entity.getWriter().getMemberIdx();
			this.postIdx = entity.getPostIdx();
			this.category = entity.getCategory().getCategory();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.postImage = entity.getPostImage();
		}
	}
	
	@Getter
	public static class PostListResponse {
		private Long memberIdx;
		private Long postIdx;
		private String title;
		private String content;
		private String postImage;
			
		public PostListResponse(Post entity) {
			this.memberIdx = entity.getWriter().getMemberIdx();
			this.postIdx = entity.getPostIdx();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.postImage = entity.getPostImage();

		}
	}
	
}
