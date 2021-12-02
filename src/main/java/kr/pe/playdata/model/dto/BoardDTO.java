package kr.pe.playdata.model.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;

public class BoardDTO {
	
	@Getter
	public class Create {
		private Long bid;
		private Long midx;
		private LocalDateTime created;
		private LocalDateTime updated;
		private String content;
	}
	
	@Getter
	public class Update {
		private Long midx;
		private String content;
	}
	
	@Getter
	public class Get {
		private Long bid;
		private Long midx;
	}
	
//	Delete는 필요 없음 - 기본으로 있고, id만 받아서 삭제하면 됨
//	@Getter
//	public class Delete {
//		private Long bid;
//	}
	
}
