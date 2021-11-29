package kr.pe.playdata.model.dto;

import lombok.Data;

public class MemberDTO {
	
	@Data
	public class Create {
		private Long idx;
		private String id;
		private String pw;
		private String nickname;
		private String email;
		private String tier;
		private String birthDate;
		private String gender;
		private String region;
	}
	
	@Data
	public class Update {
		private String pw;
		private String nickname;
		private String email;
		private String tier;
		private String birthDate;
		private String gender;
		private String region;
	}
	
	@Data
	public class Get {
		private Long idx;
	}
	
	@Data
	public class Delete {
		private Long idx;
	}
	
}
