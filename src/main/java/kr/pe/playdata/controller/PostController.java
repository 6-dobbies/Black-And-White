package kr.pe.playdata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.dto.PostDTO;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.dto.ResponseDTO.PostListResponse;
import kr.pe.playdata.model.dto.ResponseDTO.PostResponse;
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
	public List<PostListResponse> getPostListTitle(@PathVariable String title) {
		return psv.findByTitleContaining(title);
	}
	
	@GetMapping("/post/writer/{writer}")
	public List<PostListResponse> getPostListWriter(@PathVariable String Nickname) {
		return psv.findByWriter(Nickname);
	}
	
	@GetMapping("/post/cate/{category}")
	public List<PostListResponse> getPostListCategory(@PathVariable String category) {
		return psv.findByCategory(category);
	}
	
	@GetMapping("/post/content/{content}")
	public List<PostListResponse> getPostListContent(@PathVariable String content) {
		return psv.findByContentContaining(content);
	}
	
	@PostMapping("/post")
	public Long savePost(@RequestBody PostDTO.Create dto) {
		return psv.savePost(dto);
	}
	
	@PutMapping("/post/update")
	public Long updatePost(@PathVariable Long postIdx, @RequestBody PostDTO.Update dto) {
		return psv.updatePost(postIdx, dto);
	}
	
	@PatchMapping("/post/dellist")
	public List<Long> deletePostList(@RequestBody List<Long> dellist) {
		for (Long i:  dellist) {
			psv.DeletePost(i);
		}
		return dellist;
	}
	
	@PatchMapping("/post/del")
	public Long deletePost(@RequestBody Long del) {
		return psv.DeletePost(del);
	}
	
}
