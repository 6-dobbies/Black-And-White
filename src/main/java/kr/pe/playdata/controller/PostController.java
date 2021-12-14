package kr.pe.playdata.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.model.dto.ResponseDTO.PostListResponse;
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.repository.BoardRepository;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.service.PostService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RestController
public class PostController {

	private final PostService postService;
	private final ResponseService responseService;
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	// 게시글 1개 조회 - postIdx
	@GetMapping("/posts/idx/{postIdx}")
	public SingleResult<ResponseDTO.PostResponse> getPostOneIdx(@PathVariable Long postIdx) {
		return responseService.getSingleResult(postService.findByPostIdx(postIdx));
	}

	// 게시글 list 조회 - category
	@GetMapping("/posts/category/{category}")
	public ListResult<PostListResponse> getPostListCategory(@PathVariable String category) {
		return responseService.getListResult(postService.findByCategory(category));
	}

	// 게시글 list 조회 - nickname
	@GetMapping("/posts/writer/{nickname}")
	public ListResult<PostListResponse> getPostListWriter(@PathVariable String nickname) {
		return responseService.getListResult(postService.findByWriter(nickname));
	}
	
	// 게시글 list 조회 - title
	@GetMapping("/posts/title/{title}")
	public ListResult<PostListResponse> getPostListTitle(@PathVariable String title) {
		return responseService.getListResult(postService.findByTitleContaining(title));
	}
	
	// 게시글 list 조회 - content
	@GetMapping("/posts/content/{content}")
	public ListResult<PostListResponse> getPostListContent(@PathVariable String content) {
		return responseService.getListResult(postService.findByContentContaining(content));
	}
	
	// 게시글 저장
	@PostMapping("/post")
	public SingleResult<Long> savePost(@RequestBody String data) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		Board category = boardRepository.findByCategory((String) json2.get("category")).get();
		Member writer = memberRepository.findByNickname((String) json2.get("writer")).get();
		
		String title = (String) json2.get("title");
		String content = (String) json2.get("content");
		String postImage = (String) json2.get("postImage");
		int del = 0;

		Post dto = Post.builder()
					   .category(category).title(title).writer(writer)
					   .content(content).postImage(postImage).del(del).build();

		return responseService.getSingleResult(postService.savePost(dto));
	}

	// 게시글 수정
	@PutMapping("/posts/{postIdx}")
	public SingleResult<Long> updatePost(@PathVariable Long postIdx, @RequestBody String data) throws ParseException {
		return responseService.getSingleResult(postService.updatePost(postIdx, data));
	}
	
	// 게시글 삭제
	@PatchMapping("/posts/{postIdx}")
	public SingleResult<Long> deletePost(@PathVariable Long postIdx) {
		return responseService.getSingleResult(postService.deletePost(postIdx));
	}
	
	// 게시글 list 조회 - del
	@GetMapping("/posts/del/{del}")
	public ListResult<PostListResponse> getPostListDel(@PathVariable int del) {
		return responseService.getListResult(postService.findByDel(del));
	}

}
