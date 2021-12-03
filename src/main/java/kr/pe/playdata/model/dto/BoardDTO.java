package kr.pe.playdata.model.dto;

import kr.pe.playdata.model.domain.Board;
import lombok.Builder;
import lombok.Getter;

public class BoardDTO {
	
	@Getter
	public static class Create {
		private String category;
		
		@Builder
		public Create(String category) {
			this.category = category;
		}
		
		public Board toEntity() {
			return Board.builder()
						.category(category)
						.build();
		}
	}
	
	@Getter
	public static class Update {
		private String category;
	}
	
	@Getter
	public static class Get {
		private String category;
	}
	
}
