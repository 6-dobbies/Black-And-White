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
		private String postImage;
		private int del;
		
		@Builder
		public Create(Long memberIdx, Long boardIdx, String title, String content, String postImage) {
			this.memberIdx = memberIdx;
			this.boardIdx = boardIdx;
			this.title = title;
			this.content = content;
			this.postImage = postImage;
			this.del = 0;
		}
		
		public Post toEntity(Member writer, Board category) {
			return Post.builder()
					   .writer(writer)
					   .category(category)
					   .title(title)
					   .content(content)
					   .postImage(postImage)
					   .del(del)
					   .build();
		}
	}
	
	@Getter
	public static class Update {
		private Long category;
		private String title;
		private String content;
		private String postImage;
		
		@Builder
		public Update(Long category, String title, String content, String postImage) {
			this.category = category;
			this.title = title;
			this.content = content;
			this.postImage = postImage; 
		}
	}
	
	@Getter
	public static class Get {
		private Member writer;
		private Board category;
		private String title;
		private String content;
		private String postImage;
		
		public Get(Post entity) {
			this.writer = entity.getWriter();
			this.category = entity.getCategory();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.postImage = entity.getPostImage();
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
