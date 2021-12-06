package kr.pe.playdata.model.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
//oracle이라서 @SequenceGenerator 사용해야함. mysql로 바꾸면 삭제하기
//@SequenceGenerator(name="post_seq", sequenceName="post_seq", initialValue=1, allocationSize=1)
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)			//mysql에서 사용하기!! oracle은 불가
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="post_seq")	//나중에 삭제하기
	@Column(name="post_idx")
	private Long postIdx;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "writer")
	private Member writer;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "category")
	private Board category;
	
	@NotNull
	private String title;
	
	@Lob
	@NotNull
	private String content;
	
	@Column(name="post_image")
	private String postImage;
	
	@CreationTimestamp
	@Column(name="created", nullable = false, updatable = false)
	private LocalDateTime created;
	
	@UpdateTimestamp
	@Column(name="updated", nullable = false)
	private LocalDateTime updated;
	
	@Builder
	public Post(Member writer, Board category, String title, String content, String postImage) {
		this.writer = writer;
		this.category = category;
		this.title = title;
		this.content = content;
		this.postImage = postImage;
	}
	
	public Post update(Board category, String title, String content, String postImage) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.postImage = postImage;
		
		return this;
	}
	
}
