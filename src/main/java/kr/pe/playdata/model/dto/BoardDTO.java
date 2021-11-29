package kr.pe.playdata.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

public class BoardDTO {
	
	@Data
	public class Create {
		private Long bid;
		private Long midx;
		private LocalDateTime created;
		private LocalDateTime updated;
		private String content;
	
	}
	
	@Data
	public class Update {
		private Long midx;
		private String content;
	}
	
	@Data
	public class Get {
		private Long bid;
		private Long midx;
	}
	
	@Data
	public class Delete {
		private Long bid;
	}
	
}
