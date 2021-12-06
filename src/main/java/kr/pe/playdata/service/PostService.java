package kr.pe.playdata.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.BoardRepository;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository prp;
	private final BoardRepository brp;
	private final MemberRepository mrp;
	
	// postIdx로 post 하나 조회
	@Transactional(readOnly = true)
	public ResponseDTO.PostResponse findByPostIdx(Long postIdx) {
		Post entity = prp.findByPostIdx(postIdx)
				.orElseThrow(() -> new IllegalArgumentException("Post with postIdx: " + postIdx + " is not valid"));
		
		return new ResponseDTO.PostResponse(entity);
	}
	
	// categoryname으로 post 리스트 조회
	@Transactional(readOnly = true)
	public Optional<ResponseDTO.PostListResponse> findByCategory(String categoryname) {
		Optional<Board> category = brp.findByCategory(categoryname);
		return prp.findByCategory(category.get()).map(ResponseDTO.PostListResponse::new);
	}
	
	// memberIdx로 post 리스트 조회
	@Transactional(readOnly = true)
	public ResponseDTO.PostResponse findByWriter(String nickname) {
		Optional<Member> member = mrp.findByNickname(nickname);
		Post entity = prp.findByWriter(member.get())
				.orElseThrow(() -> new IllegalArgumentException("Post with memberIdx: " + nickname + " is not valid"));
		
		return new ResponseDTO.PostResponse(entity);
	}
	
	// title로 title이 포함된 post 리스트 조회
	@Transactional(readOnly = true)
	public Optional<ResponseDTO.PostListResponse> findByTitleContaining(String title) {
		return prp.findByTitleContaining(title).map(ResponseDTO.PostListResponse::new);
	}
	
	@Transactional(readOnly = true)
	public Optional<ResponseDTO.PostListResponse> findByContentContaining(String content) {
		return prp.findByContentContaining(content).map(ResponseDTO.PostListResponse::new);
	}
	
	// post 전체 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findPostAll() {
		return prp.findAll().stream()
										 .map(ResponseDTO.PostListResponse::new)
										 .collect(Collectors.toList());
	}
	
	
}
