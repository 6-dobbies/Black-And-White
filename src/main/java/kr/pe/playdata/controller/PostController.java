package kr.pe.playdata.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import kr.pe.playdata.model.response.ListResult;
import kr.pe.playdata.model.response.SingleResult;
import kr.pe.playdata.service.PostService;
import kr.pe.playdata.service.ResponseService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class PostController {
	@Autowired(required = false)
	private final PostService psv;
	private final ResponseService rs;

	@GetMapping("/post/idx/{postIdx}")
	public SingleResult<ResponseDTO.PostResponse> getPostOneIdx(@PathVariable Long postIdx) {
		return rs.getSingleResult(psv.findByPostIdx(postIdx));
	}

	@GetMapping("/post/title/{title}")
	public ListResult<PostListResponse> getPostListTitle(@PathVariable String title) {
		return rs.getListResult(psv.findByTitleContaining(title));
	}

	@GetMapping("/post/writer/{writer}")
	public ListResult<PostListResponse> getPostListWriter(@PathVariable String Nickname) {
		return rs.getListResult(psv.findByWriter(Nickname));
	}

	@GetMapping("/post/cate/{category}")
	public ListResult<PostListResponse> getPostListCategory(@PathVariable String category) {
		return rs.getListResult(psv.findByCategory(category));
	}

	@GetMapping("/post/content/{content}")
	public ListResult<PostListResponse> getPostListContent(@PathVariable String content) {
		return rs.getListResult(psv.findByContentContaining(content));
	}

	@PostMapping("/post")
	public SingleResult<Long> savePost(@RequestBody PostDTO.Create dto) {
		return rs.getSingleResult(psv.savePost(dto));
	}

	@PutMapping("/post/update")
	public SingleResult<Long> updatePost(@PathVariable Long postIdx, @RequestBody PostDTO.Update dto) {
		return rs.getSingleResult(psv.updatePost(postIdx, dto));
	}

	@PatchMapping("/post/dellist")
	public ListResult<Long> deletePostList(@RequestBody List<Long> dellist) {
		for (Long i : dellist) {
			psv.DeletePost(i);
		}
		return rs.getListResult(dellist);
	}

	@PatchMapping("/post/del")
	public SingleResult<Long> deletePost(@RequestBody Long del) {
		return rs.getSingleResult(psv.DeletePost(del));
	}

}