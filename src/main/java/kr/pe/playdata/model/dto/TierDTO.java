package kr.pe.playdata.model.dto;

import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Tier;
import lombok.Builder;
import lombok.Getter;

public class TierDTO {

	@Getter
	public static class Create {
		private Long memberIdx;
		private int win;
		private int draw;
		private int loss;
		private int play;

		@Builder
		public Create(Long memberIdx, int win, int draw, int loss, int play) {
			this.memberIdx = memberIdx;
			this.win = 0;
			this.draw = 0;
			this.loss = 0;
			this.play = 0;
		}

		public Tier toEntity(Member member) {
			return Tier.builder()
					   .member(member)
					   .win(win)
					   .draw(draw)
					   .loss(loss)
					   .play(play)
					   .build();
		}
	}

	@Getter
	public static class Update {
		private int win;
		private int draw;
		private int loss;
		private int play;

		@Builder
		public Update(int win, int draw, int loss, int play) {
			this.win = win;
			this.draw = draw;
			this.loss = loss;
			this.play = win + draw + loss;
		}
	}

}
