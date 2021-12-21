package kr.pe.playdata.model.dto;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;
import lombok.Builder;
import lombok.Getter;

public class PostDTO {

	@Getter
	public static class Create {
		private Long memberIdx;
		private Long boardIdx;
		private String title;
		private String content;
		private int del;

		@Builder
		public Create(Long memberIdx, Long boardIdx, String title, String content) {
			this.memberIdx = memberIdx;
			this.boardIdx = boardIdx;
			this.title = title;
			this.content = content;
			this.del = 0;
		}

		public Post toEntity(Member writer, Board category) {
			return Post.builder()
					   .writer(writer)
					   .category(category)
					   .title(title)
					   .content(content)
					   .del(del)
					   .build();
		}
	}

	@Getter
	public static class Update {
		private Long category;
		private String title;
		private String content;

		@Builder
		public Update(Long category, String title, String content) {
			this.category = category;
			this.title = title;
			this.content = content;
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

}
