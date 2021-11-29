package kr.pe.playdata.model.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "board")
public class Board {
	
	@Id
	@Column(name="id")
	private Long bid;
	
	//@NonNull
	@Column(name="idx")
	private Long midx;
	
	//@NonNull
	@CreationTimestamp
	@Column(name="created")
	private LocalDateTime created;
	
	//@NonNull
	@UpdateTimestamp
	@Column(name="updated")
	private LocalDateTime updated;
	
	//@NonNull
	@Column(name="content")
	private String content;
	
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
