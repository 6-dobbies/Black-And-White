package kr.pe.playdata.model.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
//oracle이라서 @SequenceGenerator 사용해야함. mysql로 바꾸면 삭제하기
//@SequenceGenerator(name="board_seq", sequenceName="board_seq", initialValue=1, allocationSize=1)
@Table(name = "board")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)			//mysql에서 사용하기!! oracle은 불가
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="board_seq")	//나중에 삭제하기
	@Column(name="board_idx")
	private Long boardIdx;
	
	@NotNull
	private String category;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonBackReference
    private List<Post> postList;
	
	@Builder
	public Board(String category) {
		this.category = category;
	}
	
}
