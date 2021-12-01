package kr.pe.playdata.model.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class Board {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)			//mysql에서 사용하기!! oracle은 불가
	@Column(name="board_idx")
	private Long boardIdx;
	
	@NotNull
	@Column(name="writer")
	private String writer;
	
	@NotNull
	@Column(name="title")
	private String title;
	
	@Lob
	@NotNull
	@Column(name="content")
	private String content;
	
	@CreationTimestamp
	@Column(name="created")
	private LocalDateTime created;
	
	@UpdateTimestamp
	@Column(name="updated")
	private LocalDateTime updated;
	
//	@Override
//	public String toString() {
//
//		return "{\"userEmail\":\"" + userEmail + "\", "
////				+ "\"userPassword\":\"" + userPassword + "\", "
//				+ "\"userNickname\":\"" + userNickname+ "\", "
//				+ "\"roles\":\"" + roles + "\", "
//				+ "\"userOut\":" + userOut + ", "
//				+ "\"assignDate\":\"" + assignDate + "\", "
//				+ "\"outDate\":\"" + outDate + "\"}";
//	}
//	
	
}
