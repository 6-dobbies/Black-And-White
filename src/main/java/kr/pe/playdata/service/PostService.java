package kr.pe.playdata.service;

import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.playdata.exception.CBoardNotFoundException;
import kr.pe.playdata.exception.CPostNotFoundException;
import kr.pe.playdata.exception.CUserNotFoundException;
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
	
	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	
	// 게시글 1개 조회 - postIdx
	@Transactional(readOnly = true)
	public ResponseDTO.PostResponse findByPostIdx(Long postIdx) {
		
		Post entity = postRepository.findByPostIdx(postIdx).orElseThrow(CPostNotFoundException::new);
		
		return new ResponseDTO.PostResponse(entity);
		
	}
	
	// 게시글 list 조회 - category
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByCategory(String category) {
		
		Board entity = boardRepository.findByCategory(category).orElseThrow(CBoardNotFoundException::new);
		
		return postRepository.findByCategory(entity)
							 .stream()
							 .map(ResponseDTO.PostListResponse::new)
							 .collect(Collectors.toList());
		
	}
	
	// 게시글 list 조회 - writer
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByWriter(String nickname) {
		
		Member entity = memberRepository.findByNickname(nickname).orElseThrow(CUserNotFoundException::new);
		
		return postRepository.findByWriter(entity)
							 .stream()
							 .map(ResponseDTO.PostListResponse::new)
							 .collect(Collectors.toList());
		
	}
	
	// 게시글 list 조회 - title 일부
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByTitleContaining(String title) {
		return postRepository.findByTitleContaining(title)
							 .stream()
							 .map(ResponseDTO.PostListResponse::new)
							 .collect(Collectors.toList());
	}
	
	// 게시글 list 조회 - title 일부
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByContentContaining(String content) {
		return  postRepository.findByContentContaining(content)
							  .stream()
							  .map(ResponseDTO.PostListResponse::new)
							  .collect(Collectors.toList());
	}
	
	// 게시글 전체 조회
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findPostAll() {
		return postRepository.findAll()
							 .stream()
							 .map(ResponseDTO.PostListResponse::new)
							 .collect(Collectors.toList());
	}
	
	// 게시글 저장
	@Transactional(rollbackFor = Exception.class)
	public Long savePost(Post dto) {
		  return postRepository.save(dto).getPostIdx();
	}

	// 게시글 수정
	@Transactional(rollbackFor = Exception.class)
	public Long updatePost(Long postIdx, String data) throws ParseException {
		
		Post post = postRepository.findByPostIdx(postIdx).orElseThrow(CPostNotFoundException::new);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject json = (JSONObject) jsonParser.parse(data);
		JSONObject json2 = (JSONObject) json.get("data");
		
		Board category = boardRepository.findByCategory((String) json2.get("category")).get();
		String title = (String) json2.get("title");
		String content = (String) json2.get("content");
		String postImage = (String) json2.get("postImage");
		
		post.update(category, title, content, postImage);
		
		return postIdx;
		
	}
	
	// 게시글 삭제
	@Transactional(rollbackFor = Exception.class)
	public Long deletePost(Long postIdx) {
		
		Post post = postRepository.findByPostIdx(postIdx).orElseThrow(CPostNotFoundException::new);
		
		post.delete(postIdx);
		
		return postIdx;
		
	}
	
	// 게시글 list 조회 - del
	@Transactional(readOnly = true)
	public List<ResponseDTO.PostListResponse> findByDel(int del) {
		return postRepository.findByDel(del)
							 .stream()
							 .map(ResponseDTO.PostListResponse::new)
							 .collect(Collectors.toList());
	}
	
}
