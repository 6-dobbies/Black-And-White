package kr.pe.playdata.model.dto;

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
		
		@Builder
		public Create(Long memberIdx, Long boardIdx, String title, String content, String postImage) {
			this.memberIdx = memberIdx;
			this.boardIdx = boardIdx;
			this.title = title;
			this.content = content;
			this.postImage = postImage;
		}
	}
	
	@Getter
	public static class Update {
		private Long midx;
		private String content;
	}
	
	@Getter
	public static class Get {
		private Long bid;
		private Long midx;
	}
	
//	Delete는 필요 없음 - 기본으로 있고, id만 받아서 삭제하면 됨
//	@Getter
//	public class Delete {
//		private Long bid;
//	}
	
}
