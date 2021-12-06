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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@NotNull
	private int del;
	
	@Builder
	public Post(Member writer, Board category, String title, String content, String postImage, int del) {
		this.writer = writer;
		this.category = category;
		this.title = title;
		this.content = content;
		this.postImage = postImage;
		this.del = del;
	}
	
	public Post update(Board category, String title, String content, String postImage) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.postImage = postImage;
		
		return this;
	}

	public Post delete(Long postIdx) {
		this.del = 1;
		
		return this;
	}
	
}
