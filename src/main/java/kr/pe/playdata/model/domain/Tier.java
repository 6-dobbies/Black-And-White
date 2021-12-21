package kr.pe.playdata.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Tier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tier_idx")
	private Long tierIdx;

	@NotNull
	@OneToOne
	@JoinColumn(name = "member")
	private Member member;

	@NotNull
	private int win;
	
	@NotNull
	private int draw;
	
	@NotNull
	private int loss;

	@NotNull
	private int play;

	@Builder
	public Tier(Member member, int win, int draw, int loss, int play) {
		this.member = member;
		this.win = win;
		this.draw = draw;
		this.loss = loss;
		this.play = play;
	}

	public Tier update(int win, int draw, int loss, int play) {
		this.win = win;
		this.draw = draw;
		this.loss = loss;
		this.play = play;

		return this;
	}

}
