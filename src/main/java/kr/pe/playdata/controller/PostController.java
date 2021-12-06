package kr.pe.playdata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.dto.ResponseDTO.PostListResponse;
import kr.pe.playdata.service.PostService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class PostController {

	private final PostService psv;
	
	@GetMapping("/post/idx/{postIdx}")
	public ResponseDTO.PostResponse getPostOneIdx(@PathVariable Long postIdx) {
		return psv.findByPostIdx(postIdx);
	}
	
	@GetMapping("/post/title/{title}")
	public Optional<ResponseDTO.PostListResponse> getPostListTitle(@PathVariable String title) {
		return psv.findByTitleContaining(title);
	}
	
	@GetMapping("/post/writer/{writer}")
	public ResponseDTO.PostResponse getPostOneWriter(@PathVariable String Nickname) {
		return psv.findByWriter(Nickname);
	}
	
	@GetMapping("/post/cate/{category}")
	public Optional<PostListResponse> getPostListCategory(@PathVariable String category) {
		return psv.findByCategory(category);
	}
	
	@GetMapping("/post/content/{content}")
	public Optional<PostListResponse> getPostListContent(@PathVariable String content) {
		return psv.findByContentContaining(content);
	}
	
}
