package kr.pe.playdata.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;
import lombok.Getter;

public class ResponseDTO {

	@Getter
	public static class MemberResponse {
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
		private List<String> role;
		private int del;
		public MemberResponse(Member entity) {
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
		private List<String> role;
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
	public static class BoardResponse {
		private Long boardIdx;
		private String category;

		public BoardResponse(Board entity) {
			this.boardIdx = entity.getBoardIdx();
			this.category = entity.getCategory();
		}
	}

	@Getter
	public static class BoardListResponse {
		private Long boardIdx;
		private String category;

		public BoardListResponse(Board entity) {
			this.boardIdx = entity.getBoardIdx();
			this.category = entity.getCategory();
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
		private String writer;
		private Long postIdx;
		private String title;
		private String content;
		private String postImage;
		private LocalDateTime created;
		private LocalDateTime updated;

		public PostListResponse(Post entity) {
			this.writer = entity.getWriter().getNickname();
			this.postIdx = entity.getPostIdx();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.postImage = entity.getPostImage();
			this.created = entity.getCreated();
			this.updated = entity.getUpdated();
		}
	}

}
