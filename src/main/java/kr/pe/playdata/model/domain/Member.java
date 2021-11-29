package kr.pe.playdata.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "member")
public class Member {
	
	@Id
	@Column(name = "idx")
	private Long midx;
	
	//@NonNull
	@Column(name = "id")
	private String mid;
	
	//@NonNull
	@Column(name = "pw")
	private String pw;
	
	//@NonNull
	@Column(name = "nickname")
	private String nickname;
	
	//@NonNull
	@Column(name = "email")
	private String email;
	
	//@NonNull
	@Column(name = "tier")
	private String tier;
	
	//@NonNull
	@Column(name = "birthDate")
	private String birthDate;
	
	//@NonNull
	@Column(name = "gender") 
	private String gender;
	
	//@NonNull
	@Column(name = "region")
	private String region;
	
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
