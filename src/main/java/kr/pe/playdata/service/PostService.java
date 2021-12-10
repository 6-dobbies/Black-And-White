package kr.pe.playdata.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.model.domain.Board;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.model.domain.Post;
import kr.pe.playdata.model.dto.PostDTO;
import kr.pe.playdata.model.dto.ResponseDTO;
import kr.pe.playdata.repository.BoardRepository;
import kr.pe.playdata.repository.MemberRepository;
import kr.pe.playdata.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	@Autowired
	private final PostRepository prp;
	@Autowired
	private final BoardRepository brp;
	@Autowired
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
	public List<ResponseDTO.PostListResponse> findByCategory(String categoryname) {
		Optional<Board> category = brp.findByCategory(categoryname);
		
		return prp.findByCategory(category.get()).stream().map(ResponseDTO.PostListResponse::new)
												 		  .collect(Collectors.toList());
	}
	
	// memberIdx로 post 리스트 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByWriter(String nickname) {
		Optional<Member> member = mrp.findByNickname(nickname);
		
		return prp.findByWriter(member.get()).stream().map(ResponseDTO.PostListResponse::new)
											 		  .collect(Collectors.toList());
	}
	
	// title로 title이 포함된 post 리스트 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByTitleContaining(String title) {
		return prp.findByTitleContaining(title).stream().map(ResponseDTO.PostListResponse::new)
														.collect(Collectors.toList());
	}
	
	// 내용물이 포함된 post 리스트 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByContentContaining(String content) {
		return  prp.findByContentContaining(content).stream().map(ResponseDTO.PostListResponse::new)
													.collect(Collectors.toList());
	}
	
	// post 전체 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findPostAll() {
		return prp.findAll().stream().map(ResponseDTO.PostListResponse::new)
									 .collect(Collectors.toList());
	}

	@Transactional
	public Long savePost(Post dto) {
//		Board board = brp.findByBoardIdx(dto.getBoardIdx())
//                .orElseThrow(() -> new IllegalArgumentException("Board with id: " + dto.getBoardIdx() + " is not valid"));
//        Member member = mrp.findByMemberIdx(dto.getMemberIdx())
//                .orElseThrow(() -> new IllegalArgumentException("Member with id: " + dto.getMemberIdx() + " is not valid"));
		  return prp.save(dto).getPostIdx();
//        return prp.save(dto.toEntity(member, board)).getPostIdx();
	}

	
	@Transactional
	public Long updatePost(Long postIdx, String data) throws ParseException {
		Post post = prp.findByPostIdx(postIdx).orElseThrow(() -> new IllegalArgumentException("Post with postIdx: " + postIdx + " is not valid"));
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		Board category = brp.findByCategory((String) json2.get("category")).get();
		String title = (String) json2.get("title");
		Member writer = mrp.findByNickname((String) json2.get("writer")).get();
		String content = (String) json2.get("content");
		String postImage = (String) json2.get("postImage");
		
		post.update(category, title, content, postImage);
		
		return postIdx;
	}

	@Transactional
	public Long DeletePost(Long postIdx) {
		Post post = prp.findByPostIdx(postIdx).orElseThrow(() -> new IllegalArgumentException("Post with postIdx: " + postIdx + " is not valid"));
		post.delete(postIdx);
		return postIdx;
	}
}
